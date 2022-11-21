package com.clever.dalo.patient.web.dto;

import lombok.Data;

@Data
public class PatientCreate {

    private String firstName;
    private String lastName;

    public PatientCreate(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }
}
