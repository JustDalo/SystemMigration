package com.clever.dalo.dao;

import com.clever.dalo.dao.model.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    Optional<NoteEntity> findByOldGuid(String oldGuid);
}
