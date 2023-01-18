package com.example.timeTable.service;

import com.example.timeTable.model.entities.CourseLab;
import com.example.timeTable.model.entities.TimeTable;
import com.example.timeTable.model.requestModel.TimeTableRequest;
import com.example.timeTable.model.responseModel.TimeTableResponse;
import com.example.timeTable.repository.CourseLabRepository;
import com.example.timeTable.repository.CourseRepository;
import com.example.timeTable.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TimeTableRepository timeTableRepository;
    @Autowired
    private CourseLabRepository courseLabRepository;
    public void createTimeTable(String classId, List<TimeTableRequest>timeTableRequestList){
        for (TimeTableRequest timeTableRequest : timeTableRequestList){
            TimeTable timeTable= new TimeTable();
            timeTable.setClassId(classId);
            timeTable.setType(timeTableRequest.getType());
            timeTable.setDay(timeTableRequest.getDay());
            timeTable.setPeriod(timeTableRequest.getPeriod());
            timeTable.setGrp(timeTableRequest.getGrp());
            CourseLab courseLab=courseLabRepository.findByCodeAndGrp(timeTableRequest.getCourseCode(),timeTableRequest.getGrp());
            if(timeTableRequest.getGrp()==null||timeTableRequest.getGrp()=="All")
            timeTable.setCourse(courseRepository.findByCode(timeTableRequest.getCourseCode()));
            else
                timeTable.setCourseLab(courseLabRepository.findByCodeAndGrp(timeTableRequest.getCourseCode(),timeTableRequest.getGrp()));
            timeTableRepository.save(timeTable);
        }
    }
    public List<TimeTableResponse>getTimeTable(String classId){
        List<TimeTable> timeTables= timeTableRepository.findByClassIdOrderByPeriodAsc(classId);
        List<TimeTableResponse>timeTableResponses = new ArrayList<>();
        for( TimeTable timeTable:timeTables){
            TimeTableResponse timeTableResponse=new TimeTableResponse();
            timeTableResponse.setType(timeTable.getType());
            timeTableResponse.setDay(timeTable.getDay());
            timeTableResponse.setPeriod(timeTable.getPeriod());
            if(timeTable.getCourse()!=null) {
                timeTableResponse.setCourseCode(timeTable.getCourse().getCode());
                timeTableResponse.setCourseName(timeTable.getCourse().getName());
                timeTableResponse.setTeacherName(timeTable.getCourse().getProfessor().getName());
            }
            else{
                timeTableResponse.setCourseCode(timeTable.getCourseLab().getCode());
                timeTableResponse.setCourseName(timeTable.getCourseLab().getName());
                timeTableResponse.setTeacherName(timeTable.getCourseLab().getProfessor().getName());
                timeTableResponse.setGrp(timeTable.getCourseLab().getGrp());
            }
            timeTableResponses.add(timeTableResponse);
        }
        return timeTableResponses;
    }
}
