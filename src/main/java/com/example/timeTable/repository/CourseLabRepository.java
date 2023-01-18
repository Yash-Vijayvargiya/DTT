package com.example.timeTable.repository;

import com.example.timeTable.model.entities.CourseLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseLabRepository extends JpaRepository<CourseLab,Long> {
    CourseLab findByCodeAndGrp(String code, String grp);
}
