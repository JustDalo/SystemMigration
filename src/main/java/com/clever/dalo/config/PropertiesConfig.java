package com.clever.dalo.config;

import com.clever.dalo.config.properties.MigrationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class PropertiesConfig {

    private final Environment env;

    @Bean
    public MigrationProperties migrationProperties() {

        return new MigrationProperties(
            env.getRequiredProperty("MIGRATION_URL")
        );
    }
}
