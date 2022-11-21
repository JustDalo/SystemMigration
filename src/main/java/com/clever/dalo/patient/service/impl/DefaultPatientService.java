package com.clever.dalo.patient.service.impl;

import com.clever.dalo.dao.PatientRepository;
import com.clever.dalo.dao.model.PatientEntity;
import com.clever.dalo.patient.exception.PatientNotFoundException;
import com.clever.dalo.patient.service.PatientService;
import com.clever.dalo.patient.web.dto.Patient;
import com.clever.dalo.patient.web.dto.PatientCreate;
import com.clever.dalo.patient.web.dto.PatientUpdate;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultPatientService implements PatientService {

    private final PatientRepository patientRepository;

    public DefaultPatientService(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }
    @Override
    public List<Patient> getPatientsAll() {
        return patientRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Patient getPatientById(Long id) {
        return convertToDto(patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id)));
    }

    @Override
    public PatientEntity createPatient(PatientCreate request) {

        PatientEntity patientToSave = new PatientEntity(request.getFirstName(), request.getLastName(), 200);

        return patientRepository.save(patientToSave);
    }

    @Override
    public PatientEntity updatePatient(Long id, PatientUpdate request) {

        PatientEntity patientToUpdate = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id));

        patientToUpdate.setFirstName(request.getFirstName());
        patientToUpdate.setLastName(request.getLastName());

        return patientRepository.save(patientToUpdate);
    }

    @Override
    public void deletePatientById(Long id) {

        try {
            patientRepository.deleteById(id);
        } catch(EmptyResultDataAccessException ex) {
            throw new PatientNotFoundException(id);
        }
    }

    private Patient convertToDto(PatientEntity patientEntity) {

        return new Patient(
                patientEntity.getFirstName(),
                patientEntity.getLastName(),
                patientEntity.getStatus()
        );
    }
}
