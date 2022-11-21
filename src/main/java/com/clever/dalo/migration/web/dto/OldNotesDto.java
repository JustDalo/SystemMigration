package com.clever.dalo.migration.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class OldNotesDto {

    private String comments;
    private String guid;
    private Timestamp modifiedAt;
    private String clientGuid;
    private String dateTime;
    private String loggedUser;
    private Timestamp createdAt;
}
