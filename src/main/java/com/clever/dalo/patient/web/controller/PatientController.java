package com.clever.dalo.patient.web.controller;

import com.clever.dalo.dao.model.PatientEntity;
import com.clever.dalo.patient.service.PatientService;
import com.clever.dalo.patient.web.dto.Patient;
import com.clever.dalo.patient.web.dto.PatientCreate;
import com.clever.dalo.patient.web.dto.PatientUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public PatientEntity createPatient(@RequestBody PatientCreate request) {
        return patientService.createPatient(request);
    }

    @GetMapping("/{patientId}")
    public Patient getPatient(@PathVariable("patientId") Long patientId) {
        return patientService.getPatientById(patientId);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getPatientsAll();
    }

    @PutMapping("/{patientId}")
    public PatientEntity updatePatient(@PathVariable("patientId") Long patientId, @RequestBody PatientUpdate request) {
        return patientService.updatePatient(patientId, request);
    }

    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable("patientId") Long patientId) {
        patientService.deletePatientById(patientId);
    }
}
