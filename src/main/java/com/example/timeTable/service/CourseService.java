package com.example.timeTable.service;

import com.example.timeTable.model.entities.Course;
import com.example.timeTable.model.entities.CourseLab;
import com.example.timeTable.model.requestModel.CourseRequest;
import com.example.timeTable.model.entities.Professor;
import com.example.timeTable.model.responseModel.CourseResponse;
import com.example.timeTable.repository.CourseLabRepository;
import com.example.timeTable.repository.CourseRepository;
import com.example.timeTable.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CourseLabRepository courseLabRepository;

    public void createCourse(CourseRequest courseRequest){
        Course course=new Course();
        course.setName(courseRequest.getName());
        course.setCode(courseRequest.getCode());
        course.setDept(courseRequest.getDept());
        course.setShortName(courseRequest.getShortName());
        Professor professor=professorRepository.findByEmailId(courseRequest.getProfessorEmail());
        course.setProfessor(professor);
        courseRepository.save(course);
    }
    public List<Course> getCoursesByProfId(Long profId){
        Professor professor=professorRepository.getReferenceById(profId);
        return professor.getCourses();
    }

    public List<CourseResponse> getAllCourses() {
        List<Course> courses= courseRepository.findAll();
        List<CourseLab> courseLabs=courseLabRepository.findAll();
        List<CourseResponse> courseResponses= new ArrayList<>();
        for(Course course : courses){
            CourseResponse courseResponse=new CourseResponse(course.getCode(),course.getName(),course.getDept(),course.getShortName(),"All");
            courseResponses.add(courseResponse);
        }
        for(CourseLab courseLab : courseLabs){
            CourseResponse courseResponse=new CourseResponse(courseLab.getCode(),courseLab.getName(),courseLab.getDept(),courseLab.getShortName(),courseLab.getGrp());
            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }

    public List<CourseResponse> getCoursesByProfEmail(String profEmail) {
        List<Course>courses=courseRepository.findByProfessor_EmailId(profEmail);
        List<CourseLab>courseLabs=courseLabRepository.findByProfessor_EmailId(profEmail);
        List<CourseResponse> courseResponses= new ArrayList<>();
        for(Course course : courses){
            CourseResponse courseResponse=new CourseResponse(course.getCode(),course.getName(),course.getDept(),course.getShortName(),"All");
            courseResponses.add(courseResponse);
        }
        for(CourseLab courseLab : courseLabs){
            CourseResponse courseResponse=new CourseResponse(courseLab.getCode(),courseLab.getName(),courseLab.getDept(),courseLab.getShortName(),courseLab.getGrp());
            courseResponses.add(courseResponse);
        }
        return courseResponses;
    }
}
