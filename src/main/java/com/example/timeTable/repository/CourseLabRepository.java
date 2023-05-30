package com.example.timeTable.repository;

import com.example.timeTable.model.entities.CourseLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseLabRepository extends JpaRepository<CourseLab,Long> {
    List<CourseLab> findByProfessor_EmailId(String emailId);
    CourseLab findByCodeAndGrp(String code, String grp);


}
