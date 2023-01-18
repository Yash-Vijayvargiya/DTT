package com.example.timeTable.repository;

import com.example.timeTable.model.entities.Changes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangesRepository extends JpaRepository<Changes,Long> {
}
