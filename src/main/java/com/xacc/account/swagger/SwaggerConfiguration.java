package com.xacc.account.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.experimental.var;

@Configuration
public class SwaggerConfiguration {
        @Bean
        public OpenAPI appDocumentation() {
                String xaccAccount = "xacc account";
                var securityScheme = new SecurityScheme()
                                .name(xaccAccount)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic");

                var components = new Components()
                                .addSecuritySchemes(xaccAccount, securityScheme);

                var securityRequirement = new SecurityRequirement().addList(xaccAccount);

                return new OpenAPI()
                                .addSecurityItem(securityRequirement)
                                .components(components);
        }
}
