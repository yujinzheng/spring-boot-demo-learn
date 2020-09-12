# spring-boot-demo-learn-admin-client

> 本 demo 主要演示了如何在 Spring Boot 中利用admin的图形化界面查看spring项目的运行情况

## admin server配置

除非对server有特殊定制，否则只需要在pom.xml中引入admin的包，然后在SpringBootDemoLearnAdminServerApplication.java文件中添加注解“@EnableAdminServer”即可。

## admin client配置

admin client不需要对“SpringBootDemoLearnAdminServerApplication.java”文件进行修改，但是需要对application.yaml文件进行修改。
```yaml
# 指定一个与admin server不同的端口
server:
  port: 8090
spring:
  # 配置client应用名
  application:
    name: spring-boot-demo-learn-admin-client
  boot:
    # 指定server的地址
    admin:
      client:
        url: "http://localhost:8080"
# 配置actuator，显示所有health信息
management:
  endpoint:
    health:
      show-details: always
  endpoints:
    web:
      exposure:
        include: '*'
# 配置日志文件的路径
logging:
  file:
    path: "logs/app.log"
```