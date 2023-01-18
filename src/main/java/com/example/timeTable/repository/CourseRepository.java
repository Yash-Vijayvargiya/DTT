package com.example.timeTable.repository;

import com.example.timeTable.model.entities.Course;
import com.example.timeTable.model.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface CourseRepository extends JpaRepository<Course,Long> {
    Course findByCode(String code);
    @Query("select c from Course c where c.professor = :professor order by c.professor")
    List<Course> findByProfId(@Param("professor") Professor professor);
}
