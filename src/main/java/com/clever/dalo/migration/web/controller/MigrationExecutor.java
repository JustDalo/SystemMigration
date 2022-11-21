package com.clever.dalo.migration.web.controller;

import com.clever.dalo.dao.PatientRepository;
import com.clever.dalo.dao.model.PatientEntity;
import com.clever.dalo.migration.service.MigrationService;
import com.clever.dalo.migration.web.dto.ClientNotesPayloadDto;
import com.clever.dalo.migration.web.dto.OldClientDto;
import com.clever.dalo.migration.web.dto.OldNotesDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MigrationExecutor {

    private final WebClient webClient;
    private final MigrationService migrationService;
    private final PatientRepository patientRepository;
    private static final String MIGRATION_DATE_FROM = "2019-09-18";

    @Scheduled(cron = "* * * * * ?")
    public void migrate() {

        List<OldClientDto> oldClients = webClient.post()
            .uri("/clients")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<OldClientDto>>() {
            })
            .block();


        List<PatientEntity> newClients = patientRepository.findAllByStatusIn(List.of(200, 210, 230));
        Objects.requireNonNull(oldClients).forEach(oldClient -> {

            Optional<PatientEntity> newPatient = getPatientFromNewSystem(newClients, oldClient);
            if (newPatient.isPresent()) {
                List<OldNotesDto> oldNotes = getClientOldNotes(oldClient);
                migrationService.importNotes(oldNotes, newPatient.get().getId());
            }
        });
    }

    private List<OldNotesDto> getClientOldNotes(OldClientDto client) {

        ClientNotesPayloadDto request = new ClientNotesPayloadDto(
            client.getAgency(),
            MIGRATION_DATE_FROM,
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            client.getGuid()
        );
        return webClient.post()
            .uri("/notes")
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .retrieve()
            .bodyToMono(
                new ParameterizedTypeReference<List<OldNotesDto>>() {
                }
            )
            .block();
    }

    private Optional<PatientEntity> getPatientFromNewSystem(List<PatientEntity> newPatients, OldClientDto oldClient) {
        return newPatients.stream()
            .filter(patient -> Arrays.asList(patient.getOldGuid().split(",")).contains(oldClient.getGuid()))
            .findFirst();
    }
}
