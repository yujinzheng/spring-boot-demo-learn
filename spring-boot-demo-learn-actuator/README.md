# spring-boot-demo-learn-actuator

> 本 demo 主要演示了如何在 Spring Boot 中通过 actuator 检查项目运行情况

## actuator基本配置

在本demo中，我们主要学习了actuator的基本配置，通过actuator，我们可以监控和度量Spring Boot应用程序。
为了实现actuator的配置，我们首先要在pom.xml文件中导入actuator包
```yaml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
导入actuator包之后，只需要在application.yaml中添加如下配置，就可以查看health信息：
```yaml
management:
  # 端点信息接口使用的端口，为了和主系统接口使用的端口进行分离
  server:
    port: 8090
  endpoint:
    health:
      show-details: always
  # 设置端点暴露的哪些内容，默认["health","info"]，设置"*"代表暴露所有可访问的端点
  endpoints:
    web:
      exposure:
        include: '*'
```
完成配置后，启动Spring，就可以在http://localhost:8090/actuator 查看actuator信息

## 自定义actuator

除了使用系统自带的监控内容，actuator还支持自定义显示内容。

### 自定义健康监控器HealthIndicator

新增CustomHealthIndicator类，让其实现HealthIndicator接口，重写health()方法，我们就可以控制健康监控器的配置信息。
```java
package com.yucoding.actuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author YU
 * @since 2020/9/12 1:09
 */
@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.down().build();
    }
}

```

### 自定义Endpoint

新增CustomEndpoint类，对其添加@Endpoint注解和@ReadOperation注解，就可以自定义一个Endpoint。
```java
package com.yucoding.actuator.config;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 * @author YU
 * @since 2020/9/12 1:11
 */
@Component
@Endpoint(id = "customEndpoint")
public class CustomEndpoint {
    @ReadOperation
    public String custom() {
        return "HelloWorld";
    }
}

```

## 配置用户名密码登录

如果对用户的操作不做限制，通过actuator将暴露很多环境信息，因此我们可以考虑引入security包，对用户进行登录校验。
首先我们在pom.xml文件中添加对security包的依赖信息
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```
然后我们在application.yaml配置文件中设置用户名和密码。
```yaml
# 若要访问端点信息，需要配置用户名和密码
spring:
  security:
    user:
      name: yucoding
      password: 123456
```
这样一来，当我们想要访问端点信息时，就必须输入用户名和密码。