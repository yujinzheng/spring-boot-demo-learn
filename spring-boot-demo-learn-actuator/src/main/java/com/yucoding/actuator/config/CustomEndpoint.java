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
