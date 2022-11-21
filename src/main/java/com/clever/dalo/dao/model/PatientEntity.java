package com.clever.dalo.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "company_user")
@NoArgsConstructor
@Data
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "old_client_guid")
    private String oldGuid;

    @Column(name = "status_id", nullable = false)
    private int status;

    public PatientEntity(String firstName, String lastName, int status) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public PatientEntity(String firstName, String lastName, String oldGuid, int status) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.oldGuid = oldGuid;
        this.status = status;
    }
}
