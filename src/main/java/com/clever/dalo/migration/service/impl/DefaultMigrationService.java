package com.clever.dalo.migration.service.impl;

import com.clever.dalo.dao.NoteRepository;
import com.clever.dalo.dao.UserRepository;
import com.clever.dalo.dao.model.NoteEntity;
import com.clever.dalo.dao.model.UserEntity;
import com.clever.dalo.migration.service.MigrationService;
import com.clever.dalo.migration.web.dto.OldNotesDto;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.List;
import java.util.Optional;

public class DefaultMigrationService implements MigrationService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final Counter counter;

    public DefaultMigrationService(NoteRepository noteRepository, UserRepository userRepository, MeterRegistry registry) {

        this.noteRepository = noteRepository;
        this.userRepository = userRepository;

        counter = registry.counter("import.counter");
    }

    @Override
    public void importNotes(List<OldNotesDto> oldNotes, Long patientId) {

        List<UserEntity> allUsers = userRepository.findAll();
        oldNotes.forEach(oldNote -> {
            UserEntity noteNewUser = getUserFromNewSystem(allUsers, oldNote.getLoggedUser());
            importNote(oldNote, noteNewUser, patientId);
        });
    }

    private void importNote(OldNotesDto oldNote, UserEntity noteNewUser, Long patientId) {

        Optional<NoteEntity> newNoteEntity = noteRepository.findByOldGuid(oldNote.getGuid());
        if (newNoteEntity.isEmpty()) {
            noteRepository.save(
                new NoteEntity(
                    oldNote.getCreatedAt(),
                    oldNote.getModifiedAt(),
                    noteNewUser.getId(),
                    noteNewUser.getId(),
                    oldNote.getComments(),
                    oldNote.getGuid(),
                    patientId
                )
            );
            counter.increment();
            return;
        }

        if (newNoteEntity.get().getModifiedAt().before(oldNote.getModifiedAt())) {

            noteRepository.save(new NoteEntity(
                oldNote.getCreatedAt(),
                oldNote.getModifiedAt(),
                noteNewUser.getId(),
                noteNewUser.getId(),
                oldNote.getComments(),
                oldNote.getGuid(),
                patientId
            ));
            counter.increment();
        }
    }

    private UserEntity getUserFromNewSystem(List<UserEntity> allUsers, String userLogin) {
        return allUsers
            .stream()
            .filter(userEntity -> userEntity.getLogin().equals(userLogin))
            .findFirst()
            .orElse(userRepository.save(new UserEntity(userLogin)));
    }
}
