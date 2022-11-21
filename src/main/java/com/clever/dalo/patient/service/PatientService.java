package com.clever.dalo.patient.service;

import com.clever.dalo.dao.model.PatientEntity;
import com.clever.dalo.patient.web.dto.Patient;
import com.clever.dalo.patient.web.dto.PatientCreate;
import com.clever.dalo.patient.web.dto.PatientUpdate;

import java.util.List;

public interface PatientService {

    List<Patient> getPatientsAll();

    Patient getPatientById(Long id);

    PatientEntity createPatient(PatientCreate request);

    PatientEntity updatePatient(Long id, PatientUpdate request);

    void deletePatientById(Long id);
}
