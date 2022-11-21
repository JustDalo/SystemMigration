package com.clever.dalo.migration.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ClientNotesPayloadDto {

    private String agency;
    private String dateFrom;
    private String dateTo;
    private String clientGuid;
}
