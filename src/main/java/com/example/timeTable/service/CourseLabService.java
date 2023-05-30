package com.example.timeTable.service;

import com.example.timeTable.model.entities.CourseLab;
import com.example.timeTable.model.entities.Professor;
import com.example.timeTable.model.requestModel.CourseLabRequest;
import com.example.timeTable.repository.CourseLabRepository;
import com.example.timeTable.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseLabService {
    @Autowired
    private CourseLabRepository courseLabRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    public void insertCourseLab(CourseLabRequest courseLabRequest){
        CourseLab courseLab= new CourseLab();
        courseLab.setCode(courseLabRequest.getCode());
        courseLab.setDept(courseLabRequest.getDept());
        courseLab.setName(courseLabRequest.getName());
        courseLab.setGrp(courseLabRequest.getGrp());
        Professor professor=professorRepository.findByEmailId(courseLabRequest.getProfessorEmail());
        courseLab.setProfessor(professor);
        courseLabRepository.save(courseLab);
    }
}
