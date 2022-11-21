package com.clever.dalo.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "patient_note")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_data_time", nullable = false)
    private Timestamp createdAt;

    @Column(name = "last_modified_date_time", nullable = false)
    private Timestamp modifiedAt;

    @Column(name = "created_by_user_id")
    private Long createdBy;

    @Column(name = "last_modified_by_user_id")
    private Long modifiedBy;

    @Column(name = "note", length = 4000)
    private String note;

    @Column(name = "old_note_guid")
    private String oldGuid;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    public NoteEntity(Timestamp createdAt, Timestamp modifiedAt, Long createdBy, Long modifiedBy, String note, String oldGuid, Long patientId) {

        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.note = note;
        this.oldGuid = oldGuid;
        this.patientId = patientId;
    }
}
