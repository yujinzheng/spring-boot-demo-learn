# spring-boot-demo-properties

> 本 demo 演示如何获取配置文件的自定义配置，以及如何多环境下的配置文件信息的获取

## 自定义配置

在本项目中，我们配置了文件"additional-spring-configuration-metadata.json"，这个文件中定义的属性可以在"application.yaml"文件中进行联想。

```json
{
   "properties": [
     {
       "name": "application.name",
       "description": "Default value is artifactId in pom.xml.",
       "type": "java.lang.String"
     },
     {
       "name": "application.version",
       "description": "Default value is version in pom.xml.",
       "type": "java.lang.String"
     },
     {
       "name": "developer.name",
       "description": "The Developer Name.",
       "type": "java.lang.String"
     },
     {
       "name": "developer.website",
       "description": "The Developer Website.",
       "type": "java.lang.String"
     },
     {
       "name": "developer.qq",
       "description": "The Developer QQ Number.",
       "type": "java.lang.String"
     },
     {
       "name": "developer.phone-number",
       "description": "The Developer Phone Number.",
       "type": "java.lang.String"
     }
   ]
 }
```

该配置文件来源于"org.springframework.boot.spring-boot-autoconfigure"，找到这个jar包，打开之后可以发现在它里面同样有一个/META-INF/additional-spring-configuration-metadata.json文件，里面配置的就是能够在"application.yaml"文件中进行联想的关键字，我们按照这个jar包的文件结构同样配置了"additional-spring-configuration-metadata.json"，就是在其基础上的补充。

## 多环境中配置文件信息的获取

在本项目中，我们一共配置了三个配置文件："application.yaml"，"application-dev.yaml"，"application-prod.yaml"。

在这三个文件中，"application.yaml"是主配置文件，可以通过主配置文件中的"active"参数来决定选用哪个配置文件。

```yaml
server:
  port: 8080
  servlet:
    context-path: /demo
spring:
  profiles:
    active: dev
```