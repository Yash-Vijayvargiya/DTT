package com.example.timeTable.repository;

import com.example.timeTable.model.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    Professor findByEmailId(String emailId);
}
