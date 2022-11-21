package com.clever.dalo.patient.web.dto;

import lombok.Data;

@Data
public class PatientUpdate {

    private String firstName;
    private String lastName;

    public PatientUpdate(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }
}
