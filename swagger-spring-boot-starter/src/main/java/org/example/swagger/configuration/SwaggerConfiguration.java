package org.example.swagger.configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.Api;
import org.example.swagger.properties.SwaggerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Swagger加载配置
 *
 * @author: 苦瓜不苦
 * @date: 2022/7/2 21:46
 **/
@Configuration
@EnableKnife4j
@EnableSwagger2WebMvc
@ConditionalOnProperty(prefix = "knife4j", name = "enable", havingValue = "true")
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {


    private final SwaggerProperties swaggerProperties;

    private final OpenApiExtensionResolver openApiExtensionResolver;

    public SwaggerConfiguration(SwaggerProperties swaggerProperties, OpenApiExtensionResolver openApiExtensionResolver) {
        this.swaggerProperties = swaggerProperties;
        this.openApiExtensionResolver = openApiExtensionResolver;
    }


    /**
     * 配置swagger文档
     *
     * @return
     */
    @Bean
    public Docket defaultDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(swaggerProperties.getTitle())
                        .description(swaggerProperties.getDescription())
                        .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                        .contact(new Contact(swaggerProperties.getAuthor(), swaggerProperties.getUrl(), swaggerProperties.getEmail()))
                        .version(swaggerProperties.getVersion())
                        .build())
                .groupName(swaggerProperties.getGroupName())
                .ignoredParameterTypes(className())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildSettingExtensions());
    }

    /**
     * 获取字节码
     *
     * @return 字节码
     */
    private Class<?> className() {
        try {
            return Class.forName(swaggerProperties.getClassName());
        } catch (ClassNotFoundException e) {
            return null;
        }
    }


}
