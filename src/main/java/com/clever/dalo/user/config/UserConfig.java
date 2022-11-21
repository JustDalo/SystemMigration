package com.clever.dalo.user.config;

import com.clever.dalo.dao.UserRepository;
import com.clever.dalo.user.service.UserService;
import com.clever.dalo.user.service.impl.DefaultUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    private final UserRepository userRepository;

    public UserConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserService userService() {

        return new DefaultUserService(userRepository);
    }
}
