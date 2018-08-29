package com.configuration;

import com.google.common.base.Predicates;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)//
            .select()//
            .apis(RequestHandlerSelectors.any())//
            .paths(Predicates.not(PathSelectors.regex("/error")))//
            .build()//
            .apiInfo(metadata())//
            .useDefaultResponseMessages(false)//
            .securitySchemes(new ArrayList<>(Arrays.asList(new ApiKey("Goclean %token", "Authorization", "Header"))))//
            .tags(new Tag("users", "Operations about users"))//
            .tags(new Tag("ping", "Just a ping"))//
            .genericModelSubstitutes(Optional.class);

  }

  private ApiInfo metadata() {
    return new ApiInfoBuilder()//
            .title("GoClean API")//
            .description(
                    "This is a sample GoClean JWT authentication service. You can find out more about JWT at [https://jwt.io/](https://jwt.io/).  " +
                            "Для тестирования вы должны зарегистрироваться заполнив поля JSON-модели в /users/signup, либо авторизироваться /users/signin, в случае успеха - в теле ответа вернётся токен. Для тестирования остальных методов а также фильтра токена доступа необходимо в верхнем правом углу нажать кнопку 'Auhtorize' и вставить полученный токен в поле 'value:' с префиксом 'Goclean '. Например: Goclean eyJhbGciOiJIUz......")//
            .version("1.0.0")//
            .license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
            .contact(new Contact("team of 'GoClean'", "https://evo.ithillel.ua/", "odessa@ithillel.ua"))//
            .build();
  }
}
