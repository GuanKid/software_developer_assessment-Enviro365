package com.enviro.assessment.junior.desiregwanzura.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI investmentApi() {

        return new OpenAPI()

                .info(new Info()

                        .title("Investment Management API")

                        .description("""
                                REST API for managing investor portfolios,
                                processing withdrawals and exporting withdrawal history.
                                """)

                        .version("1.0.0")

                        .contact(new Contact()

                                .name("Desire Gwanzura")

                                .email("gwanzuradesire@gmail.com")

                                .url("https://github.com/yourusername"))

                        .license(new License()

                                .name("MIT License")

                                .url("https://opensource.org/licenses/MIT")))

                .externalDocs(new ExternalDocumentation()

                        .description("Project Repository")

                        .url("https://github.com/yourusername/investment-management-system"));

    }

}