package com.xacc.account.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {
    // TODO but bean dengan retur Securrity Filter Chain, parameter HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.authorizeHttpRequests(request -> request.requestMatchers(
                "/swagger-ui/**",
                "/swagger-resource/**",
                "/webjars/**",
                "/v3/api-docs/**",
                "/swagger-ui-html").permitAll() // 1

                .requestMatchers("/auth/customer").hasRole("CUSTOMER") // hasrole tidak perlu pake role
                .requestMatchers("/auth/seller").hasAuthority("ROLE_SELLER")
                .requestMatchers("/auth/debug/session").permitAll() // 2
                .anyRequest()
                .authenticated())

                // .formLogin(form -> form.permitAll())
                .httpBasic(Customizer.withDefaults())// mengubah session menjadi stateLess
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));// mengubah
                                                                                                              // session
                                                                                                              // menjadi
                                                                                                              // stateLess

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer"))
                .roles("CUSTOMER")
                .build();

        UserDetails seller = User.builder()
                .username("seller")
                .password(passwordEncoder().encode("seller"))
                .roles("SELLER")
                .build();

        return new InMemoryUserDetailsManager(customer, seller);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

}
