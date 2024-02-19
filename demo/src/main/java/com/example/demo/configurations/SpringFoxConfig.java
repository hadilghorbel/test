package com.example.demo.configurations;


import org.springframework.ws.config.annotation.WsConfigurerAdapter;

//@Configuration
public class SpringFoxConfig extends WsConfigurerAdapter {
/*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo () {
        return new ApiInfoBuilder()
                .title("Swagger Configuration")
                .description("\"Spring Boot Swagger configuration\"")
                .version("1.0").build();
    }
    */

}
