package com.clever.dalo.dao;

import com.clever.dalo.dao.model.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    List<PatientEntity> findAllByStatusIn(List<Integer> statuses);
}
