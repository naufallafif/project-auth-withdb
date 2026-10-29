package com.xacc.account.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

                .requestMatchers("/auth/customer").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers("/auth/seller").hasAnyRole("ADMIN", "SELLER")
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
    public PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

}
