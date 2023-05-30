package com.example.timeTable.controller;

import com.example.timeTable.model.responseModel.TimeTableResponse;
import com.example.timeTable.service.TimeTableService;
import org.hibernate.dialect.temptable.TemporaryTableSessionUidColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student/")
@CrossOrigin(origins = "*")

public class StudentController {
    @Autowired
    private TimeTableService timeTableService;
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
    @GetMapping("/timetable/getWeek/{classId}/{group}/{date}")
    public List<TimeTableResponse> getWeekTimeTable(@PathVariable("classId")String classId, @PathVariable ("group")String group, @PathVariable ("date")String date){
        return timeTableService.getWeekTimeTable(classId, date, group);
    }
}
