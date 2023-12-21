package com.penguin.penguinmall.user;

import com.penguin.penguinmall.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.penguin.penguinmall")
public class UserApp implements CommandLineRunner {
    @Autowired
    private IUserService ius;
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class);
    }

    @Override
    public void run(String... args) throws Exception {
        ius.warnUpUsernames();
    }
}
