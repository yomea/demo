package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuzhenhong
 * @date 2024/12/12 8:40
 */
@RestController(value = "sdf")
public class TestController {

    @GetMapping("/test")
    public User get() {
        User user = new User();
        return user;
    }

}
