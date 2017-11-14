package com.svw.tbox.tcloud.mqtt;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     * 通过createRestApi函数创建Docket的Bean之后，
     * apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
     * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现；
     * apis(RequestHandlerSelectors.basePackage())指定扫描的包下所有Controller定义的API
     * 产生文档内容（除了被@ApiIgnore指定的请求）。
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.svw.tbox.tcloud.mqtt.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("micro SERVICE RESTful-APIs")
                .description("Restful-API文档,POST添加,GET获取,PUT修改,DELETE删除")
                .contact(new Contact("liuyunlong","", "1637761016@qq.com"))
                .version("1.0")
                .build();
    }
}
