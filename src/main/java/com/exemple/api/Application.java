package com.exemple.api;

import com.exemple.api.domain.entity.User;
import com.exemple.api.domain.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner createUser(UserService userService) {
        return args -> userService.create(new User("email@email.com", "12345678", "user"));
    }
}
