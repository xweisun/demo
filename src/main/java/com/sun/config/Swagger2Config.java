package com.sun.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;
import java.util.stream.Stream;


@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(addAccessTokenParameters())
                .apiInfo(apiInfo())
                .groupName("测试测试")
                .select()
                //.apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //.apis(RequestHandlerSelectors.basePackage("com.sun.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        HashMap<String,String> map11 = new HashMap<>();
        map11.put("","");
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream();
        Integer reduce = stream.reduce(0, (i1, i2) -> i1 + i2);
        //Optional<Integer> reduce1 = stream.reduce(() -> { });
        return new ApiInfoBuilder()
                .title("测试API")
                .description("测试 ")
                .version("0.0.001")
                //描述作者信息
                .contact(new Contact("接口信息","www.com.cn","swxx@qq.com"))
                .contact(new Contact("接口信息","www.com.cn","swxx@qq.com"))
                .build();
    }
    /**
     * 在header中添加ocmtoken
     *
     * @return
     */
    private List<Parameter> addAccessTokenParameters() {
        List<Parameter> listParameter = new ArrayList<>();
        listParameter.add(new ParameterBuilder().name("Token")
                .description("访问令牌").parameterType("header")
                .defaultValue("specialurl_56756756")
                .required(true).modelRef(new ModelRef("string")).build());
        return listParameter;
    }
}

