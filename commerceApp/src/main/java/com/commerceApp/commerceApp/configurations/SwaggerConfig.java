package com.commerceApp.commerceApp.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact("Ameesha Swaroop", "www.tothenew.com", "ameesha.swaroop@tothenew.com");
    public static final ApiInfo DEFAULT = new ApiInfo("My API DOCUMENTATION", "My Api Documentation", "1.0", "urn:tos",
            DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.commerceApp.commerceApp.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT)
                .securitySchemes(Arrays.asList(apiKey()));
    }
    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

}



