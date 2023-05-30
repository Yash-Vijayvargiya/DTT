package com.example.timeTable.controller;

import com.example.timeTable.model.entities.Course;
import com.example.timeTable.model.requestModel.CourseLabRequest;
import com.example.timeTable.model.requestModel.CourseRequest;
import com.example.timeTable.model.requestModel.TimeTableRequest;
import com.example.timeTable.model.responseModel.CourseResponse;
import com.example.timeTable.model.responseModel.ProfessorResponse;
import com.example.timeTable.model.responseModel.TimeTableResponse;
import com.example.timeTable.service.CourseLabService;
import com.example.timeTable.service.CourseService;
import com.example.timeTable.service.ProfessorService;
import com.example.timeTable.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TimeTableService timeTableService;

    @Autowired
    private CourseLabService courseLabService;

    @GetMapping("/timetable/get/{branch}/{sem}")
    public List<TimeTableResponse> getTimeTable(@PathVariable("branch") String branch,@PathVariable ("sem")String sem){
        return timeTableService.getTimeTable(branch.toUpperCase()+"_"+sem);
    }

    @GetMapping("/timetable/getWeek/{branch}/{sem}/{group}/{date}")
    public List<TimeTableResponse> getWeekTimeTable(@PathVariable("branch") String branch,@PathVariable ("sem")String sem, @PathVariable ("group")String group, @PathVariable ("date")String date){
        return timeTableService.getWeekTimeTable(branch.toUpperCase()+"_"+sem, date, group);
    }

    @PostMapping("/course")
    public String createCourse(@RequestBody CourseRequest courseRequest){
        courseService.createCourse(courseRequest);
        return "Successful Insert";
    }

    @PostMapping("/timetable/{branch}/{sem}")
    public String createTimeTable(@PathVariable("branch") String branch,@PathVariable("sem")String sem,@RequestBody List<TimeTableRequest> timeTableRequestList){
        timeTableService.createTimeTable(branch.toUpperCase()+"_"+sem,timeTableRequestList);
        return "Successful Insert";
    }

    @GetMapping("/courses")
    public List<CourseResponse> getAllCourses(){
        List<CourseResponse> courses= courseService.getAllCourses();
        return courses;
    }
    @GetMapping("/professors")
    public List<ProfessorResponse> getAllProfessors(){
        List<ProfessorResponse> professorResponses=professorService.getAllProfessor();
        return professorResponses;
    }

    @PostMapping("/courseLab")
    public String createCourseLab(@RequestBody CourseLabRequest courseLabRequest){
        courseLabService.insertCourseLab(courseLabRequest);
        return "Successful Insert";

    }
    @GetMapping("/{profEmail}/courses")
    public List<CourseResponse> getCoursesByProfId(@PathVariable("profEmail") String profEmail){
        List<CourseResponse> courses= courseService.getCoursesByProfEmail((profEmail));
        return courses;
    }

}
