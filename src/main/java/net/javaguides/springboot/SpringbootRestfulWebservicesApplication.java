package net.javaguides.springboot;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot Rest API Documentation",
                description = "Spring Boot Rest API Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Yash",
                        email = "yash@yash.com",
                        url = "https://www.yash.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.yash.com/license"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Spring boot User Management Documentation",
                url = "https://www.yash.com/user_magagement.html"
        )

)
public class SpringbootRestfulWebservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
