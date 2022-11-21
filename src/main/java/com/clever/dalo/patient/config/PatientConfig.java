package com.clever.dalo.patient.config;

import com.clever.dalo.dao.PatientRepository;
import com.clever.dalo.patient.service.PatientService;
import com.clever.dalo.patient.service.impl.DefaultPatientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatientConfig {

    private final PatientRepository patientRepository;

    public PatientConfig(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Bean
    public PatientService patientService() {

        return new DefaultPatientService(patientRepository);
    }
}
