package com.clever.dalo.migration.service;

import com.clever.dalo.migration.web.dto.OldNotesDto;

import java.util.List;

public interface MigrationService {

    void importNotes(List<OldNotesDto> oldNotes, Long patientId);
}
