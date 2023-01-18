package com.example.timeTable.service;

import com.example.timeTable.model.entities.Course;
import com.example.timeTable.model.requestModel.CourseRequest;
import com.example.timeTable.model.entities.Professor;
import com.example.timeTable.repository.CourseRepository;
import com.example.timeTable.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    public void createCourse(CourseRequest courseRequest){
        Course course=new Course();
        course.setName(courseRequest.getName());
        course.setCode(courseRequest.getCode());
        course.setDept(courseRequest.getDept());
        Professor professor=professorRepository.findByEmailId(courseRequest.getProfessorRequest().getEmailId());
        course.setProfessor(professor);
        courseRepository.save(course);
    }
    public List<Course> getCoursesByProfId(Long profId){
        Professor professor=professorRepository.getReferenceById(profId);
        return professor.getCourses();
    }
}
