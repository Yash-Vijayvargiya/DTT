package com.example.timeTable.controller;

import com.example.timeTable.model.entities.Course;
import com.example.timeTable.model.requestModel.ChangeRequest;
import com.example.timeTable.model.responseModel.CourseResponse;
import com.example.timeTable.model.responseModel.TimeTableResponse;
import com.example.timeTable.repository.ChangesRepository;
import com.example.timeTable.repository.CourseRepository;
import com.example.timeTable.repository.TimeTableRepository;
import com.example.timeTable.service.ChangesService;
import com.example.timeTable.service.CourseService;
import com.example.timeTable.service.ProfessorService;
import com.example.timeTable.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/professor/")
public class ProfessorController {

  @Autowired
  private ChangesService changesService;
  @Autowired
  private ProfessorService professorService;
  @Autowired
  private CourseService courseService;
  @Autowired
  private TimeTableService timeTableService;
  @PostMapping("/addChange/{branch}/{sem}")
  public String addChange(
      @PathVariable("branch") String branch,
      @PathVariable("sem")String sem,
      @RequestBody ChangeRequest changeRequest){
    changesService.addChange(branch+"_"+sem, changeRequest);
    return "Successfully Done";
  }
  @GetMapping("/getTimetable/{profId}")
  public List<TimeTableResponse> getProfTimeTable(@PathVariable("profId") String profId){
    return professorService.getProfTimeTable(profId);
  }
  @GetMapping("/{profEmail}/courses")
  public List<CourseResponse> getCoursesByProfId(@PathVariable("profEmail") String profEmail){
    List<CourseResponse> courses= courseService.getCoursesByProfEmail((profEmail));
    return courses;
  }
  @GetMapping("/timetable/{branch}/{sem}")
  public List<TimeTableResponse> getTimeTable(@PathVariable("branch") String branch,@PathVariable ("sem")String sem){
    return timeTableService.getTimeTable(branch+"_"+sem);
  }

}
