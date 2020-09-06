package com.yucoding.helloworld;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * SpringBoot启动类
 * </p>
 *
 * @package: com.yucoding.helloworld
 * @description: SpringBoot启动类
 * @author: jinzheng.yu
 * @data: Created in 2020/09/06 11:22 PM
 * @copyright: Copyright (c)
 * @version: V1.0
 * @modified: jinzheng.yu
 */
@SpringBootApplication
@RestController
public class SpringBootDemoLearnHelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoLearnHelloworldApplication.class, args);
    }

    /**
     * Hello, World
     *
     * @param who 参数，非必须
     * @return Hello, ${who}
     */
    @GetMapping("/hello")
    public String sayHello(@RequestParam(required = false, name = "who") String who) {
        if (StrUtil.isBlank(who)) {
            who = "World";
        }
        return StrUtil.format("Hello, {}!", who);
    }
}
