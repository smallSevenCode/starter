package org.example.swagger.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Swagger主页配置
 *
 * @author: 苦瓜不苦
 * @date: 2022/7/2 21:43
 **/
@Data
@ConfigurationProperties("knife4j.home")
public class SwaggerProperties {

    /**
     * 排除参数扫描的全限定类名称
     */
    private String className;

    /**
     * 标题
     */
    private String title = "Swagger文档";

    /**
     * 分组
     */
    private String groupName = "item";

    /**
     * 描述
     */
    private String description = "前后端接口快速对接文档";

    /**
     * 作者
     */
    private String author = "苦瓜不苦";

    /**
     * 路径
     */
    private String url;

    /**
     * 邮箱
     */
    private String email = "pen_zp@163.com";

    /**
     * 地址
     */
    private String termsOfServiceUrl = "https:xxx.com";

    /**
     * 版本
     */
    private String version = "1.0-SNAPSHOT";


}
