package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
//    @Bean
//    public UiConfiguration uiConfig(){
//        return UiConfigurationBuilder.builder()
//                .deepLinking(true)
//                .displayOperationId(false)
//                .defaultModelExpandDepth(0)
//                .defaultModelsExpandDepth(-1)
//                .build();
//    }
@Bean
public Docket getApiInfo() {
//    ParameterBuilder ticketPar = new ParameterBuilder();
//    List<Parameter> pars = new ArrayList<Parameter>();
//    ticketPar.name("authorization").description("user ticket")//Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
//            .modelRef(new ModelRef("string")).parameterType("header")
//            .required(false).build(); //header中的ticket参数非必填，传空也可以
//    pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
    return new Docket(DocumentationType.SWAGGER_2)
//            .groupName("outer api")
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
//            .apiInfo(outApiInfo())
//            .securitySchemes(unifiedAuth());
//            .globalOperationParameters(pars);

}
//    private static List<ApiKey> unifiedAuth() {
//        List<ApiKey> arrayList = new ArrayList();
//        arrayList.add(new ApiKey("authorization", "token", "header"));
//        return arrayList;
//    }

//    private ApiInfo outApiInfo() {
//        return new ApiInfoBuilder()
//                .title("mylearn 前后端接口-外部") // title 标题
//                .description("外部接口文档") // description 描述 标题下
//                .version("1.0.0") // version
////                "http://mylearn/*", // termsOfService
//                .contact("bug") // contact
//                .license("Apache 2.0") // licence
////                .licenseUrl()"http://www.apache.org/licenses/LICENSE-2.0.html" // licence url
//                .build();
//
//
//    }
}
