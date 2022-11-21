package com.clever.dalo.patient.web.dto;

import lombok.Data;

@Data
public class Patient {

    private String firstName;
    private String lastName;
    private int status;

    public Patient(String firstName, String lastName, int status) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }
}
