# logback-transmittable-threadLocal

重写MDCAdapter接口,替换ThreadLocal类

# 注意

重写MDCAdapter接口，使系统使用MDC时实际上是使用我们实现的MDCAdapter接口。

在自定义MDCAdapter时要注意包名和logback的MDCAdapter一直，因为我们要在程序启动时替换MDC中的MDCAdapter，MDC的MDCAdapter是包级私有化的，所以自定义MDCAdapter的包名一定要和logback的MDCAdapter保证一致

# 软件架构

ThreadPoolTaskExecutorConfigure、ThreadPoolExecutorConfigure线程池

MDCHandlerInterceptor类MVC拦截器

MDCRequestInterceptor实例feign拦截器

# 引入依赖

```xml

<dependencies>
    <!--自定义依赖-->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>logback-transmittable-threadLocal</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    <!--web里面包括logback依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--隔离线程-->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>transmittable-thread-local</artifactId>
    </dependency>
    <!--单体服务可无需使用-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
</dependencies>
```

# 实现操作

将MDCHandlerInterceptor拦截器配置到MVC中

```java
/**
 * web配置
 *
 * @author: 苦瓜不苦
 * @date: 2022/1/4 20:34
 */
@Slf4j
@Configuration
public class WebMcvConfig implements WebMvcConfigurer {
    /**
     * 实现拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MDCHandlerInterceptor())
                .addPathPatterns("/**");
    }
}

```

# logback-spring.xml日志配置

```xml
<!--控制台日志-->
<appender name="consoleAppender" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
        <!--traceId是链路追踪唯一ID-->
        <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度,%msg：日志消息，%n是换行符-->
        <charset>UTF-8</charset>
        <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %clr(%-5level){green} %X{traceId} %-5.5L %clr(%-40.40logger{36}){cyan} : %clr(%M){magenta} - %msg%xEx%n</pattern>
    </encoder>
    <!--自定义日志过滤器-->
    <filter class="org.example.config.LogFilterConfig"/>
</appender>
```

# yml配置线程池

不配置使用默认值

```yaml
thread:
  # 核心线程数
  corePoolSize: 5
  # 队列大小
  queueCapacity: 1000
  # 线程活跃时间(秒)
  keepAliveSeconds: 60
  # 最大线程数
  maxPoolSize: 20
  # 默认线程池名称
  threadNamePrefix: 未定义
  # 是否所有任务结束之后再关闭线程
  waitForJobsToCompleteOnShutdown: true

```

# 开启多线程

提供了`ThreadUtil`工具类，可以直接获取到线程池中的线程



