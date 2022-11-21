package com.clever.dalo.patient.exception;

public class PatientNotFoundException extends RuntimeException {

    private final Long id;

    public PatientNotFoundException(Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
