//package com.example.java_demo_test.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration // spring託管成配置類
//@EnableOpenApi // 啟用swagger
//@EnableWebMvc // 引入DelegatingWebMvcConfiguration配置類，並啟用spring MVC
//public class SwaggerConfig {
//
//  private ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()
//          .title("Restful API") // 標題
//          .description("IDEA_java_demo_test") // 說明
//          //.termsOfServiceUrl("urn:tos")
//          //.contact(new Contact("DEFAULT","","")) // 聯絡人
//          //.license("Apache 2.0")
//          //.version("V3") // 版本
//          //.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.txt")
//          .build();
//
//  @Bean
//  public Docket api(){
//    return new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(DEFAULT_API_INFO)
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.example.java_demo_test.controller"))
//            .paths(PathSelectors.any())
//            .build();
//  }
//
//}
