package com.clever.dalo.migration.config;

import com.clever.dalo.config.properties.MigrationProperties;
import com.clever.dalo.dao.NoteRepository;
import com.clever.dalo.dao.UserRepository;
import com.clever.dalo.migration.service.MigrationService;
import com.clever.dalo.migration.service.impl.DefaultMigrationService;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class MigrationConfig {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final MigrationProperties migrationProperties;
    private final MeterRegistry registry;

    @Bean
    public MigrationService migrationService() {

        return new DefaultMigrationService(noteRepository, userRepository, registry);
    }

    @Bean
    public WebClient webClient() {

        return WebClient.create(migrationProperties.getUrl());
    }
}
