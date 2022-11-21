package com.clever.dalo.migration.web.dto;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class OldClientDto {

    private String agency;
    private String guid;
    private String firstName;
    private String lastName;
    private String status;
    private Date dateOfBirth;
    private Timestamp createdAt;
}
