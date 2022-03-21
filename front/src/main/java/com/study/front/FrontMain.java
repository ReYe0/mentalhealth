package com.study.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: mentalhealth
 * @Author: 二爷
 * @E-mail: 1299461580@qq.com
 * @Date: 2022/3/6 16:22
 */
@CrossOrigin
@SpringBootApplication
public class FrontMain {
    public static void main(String[] args) {
        SpringApplication.run(FrontMain.class,args);
    }
}
