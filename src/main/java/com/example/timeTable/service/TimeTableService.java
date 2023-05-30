package com.example.timeTable.service;

import com.example.timeTable.model.entities.Change;
import com.example.timeTable.model.entities.CourseLab;
import com.example.timeTable.model.entities.TimeTable;
import com.example.timeTable.model.requestModel.TimeTableRequest;
import com.example.timeTable.model.responseModel.TimeTableResponse;
import com.example.timeTable.repository.ChangesRepository;
import com.example.timeTable.repository.CourseLabRepository;
import com.example.timeTable.repository.CourseRepository;
import com.example.timeTable.repository.TimeTableRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

@Service
public class TimeTableService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private CourseLabRepository courseLabRepository;

    @Autowired
    private ChangesRepository changesRepository;

    public void createTimeTable(String classId, List<TimeTableRequest>timeTableRequestList){
        for (TimeTableRequest timeTableRequest : timeTableRequestList){
            TimeTable timeTable = timeTableRepository.findTimeTableByPeriodAndGrpAndDayAndClassId(
              timeTableRequest.getPeriod(),
              timeTableRequest.getGrp(),
              timeTableRequest.getDay(),
              classId
            );
            if(timeTable == null)
                timeTable= new TimeTable();
            timeTable.setClassId(classId);
            timeTable.setType(timeTableRequest.getType());
            timeTable.setDay(timeTableRequest.getDay());
            timeTable.setPeriod(timeTableRequest.getPeriod());
            timeTable.setGrp(timeTableRequest.getGrp());
            timeTable.setLoc(timeTableRequest.getLoc());

            if(timeTableRequest.getType()!=null && (timeTableRequest.getType().equalsIgnoreCase("class") || timeTableRequest.getType().equalsIgnoreCase("tut")))
            timeTable.setCourse(courseRepository.findByCode(timeTableRequest.getCourseCode()));
            else if(timeTableRequest.getType()!=null)
                timeTable.setCourseLab(courseLabRepository.findByCodeAndGrp(timeTableRequest.getCourseCode(),timeTableRequest.getGrp()));
            timeTableRepository.save(timeTable);
        }
    }
    public List<TimeTableResponse>getTimeTable(String classId){
        List<TimeTable> timeTables= timeTableRepository.findByClassIdOrderByPeriodAsc(classId);
        List<TimeTableResponse>timeTableResponses = new ArrayList<>();
        for( TimeTable timeTable:timeTables){
            if(timeTable.getCourse()==null && timeTable.getCourseLab()==null)continue;
            TimeTableResponse timeTableResponse=new TimeTableResponse();
            timeTableResponse.setType(timeTable.getType());
            timeTableResponse.setDay(timeTable.getDay());
            timeTableResponse.setPeriod(timeTable.getPeriod());
            timeTableResponse.setGrp(timeTable.getGrp());
            timeTableResponse.setClassId(timeTable.getClassId());
            if(timeTable.getCourse()!=null) {
                timeTableResponse.setCourseCode(timeTable.getCourse().getCode());
                timeTableResponse.setCourseName(timeTable.getCourse().getShortName());
                timeTableResponse.setTeacherName(timeTable.getCourse().getProfessor().getName());
                timeTableResponse.setLoc(timeTable.getLoc());

            }
            else if(timeTable.getCourseLab() != null){
                timeTableResponse.setCourseCode(timeTable.getCourseLab().getCode());
                timeTableResponse.setCourseName(timeTable.getCourseLab().getShortName());
                timeTableResponse.setTeacherName(timeTable.getCourseLab().getProfessor().getName());
                timeTableResponse.setLoc(timeTable.getLoc());
            }
            timeTableResponses.add(timeTableResponse);
        }
        return timeTableResponses;
    }

    public List<TimeTableResponse> getWeekTimeTable(String classId, String dateString, String group){
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.BASIC_ISO_DATE);
        String day = String.valueOf(date.getDayOfWeek());
        List<String> days = List.of("Mon", "Tues", "Wed", "Thur", "Fri");
        int weekDay = (days.indexOf(day)+7)%7;
        LocalDate startDate = date.minusDays(weekDay);
        LocalDate endDate = date.plusDays(7-weekDay);

//        List<TimeTable> timeTables= timeTableRepository.findByClassIdOrderByPeriodAsc(classId); // To get timetables of all groups, uncomment this and comment below line
        List<TimeTable> timeTables= timeTableRepository.getTimeTableByClassIdAndGrpInOrderByPeriodAsc(classId, List.of("All", group));
        List<Change> changes = changesRepository.getChangesByTimeTableInAndDateOutAfterAndDateInBefore(timeTables, Date.valueOf(startDate), Date.valueOf(endDate));
        List<TimeTableResponse> response  = new ArrayList<>();

        Map<Long, Change> timeTableChange = new HashMap<>();

        for(Change change: changes){
            timeTableChange.put(change.getTimeTable().getId(), change);
        }

        for(TimeTable timeTable: timeTables){
            if(timeTable.getCourseLab()==null&&timeTable.getCourse()==null)continue;
            TimeTableResponse timeTableResponse=new TimeTableResponse();

            Change change = timeTableChange.get(timeTable.getId());

            if(change == null){
                timeTableResponse.setType(timeTable.getType());
                if(timeTable.getCourse()!=null) {
                    timeTableResponse.setCourseCode(timeTable.getCourse().getCode());
                    timeTableResponse.setCourseName(timeTable.getCourse().getShortName());
                    if(timeTable.getCourse().getProfessor()!=null)
                    timeTableResponse.setTeacherName(timeTable.getCourse().getProfessor().getName());
                }
                else if(timeTable.getCourseLab() != null){
                    timeTableResponse.setCourseCode(timeTable.getCourseLab().getCode());
                    timeTableResponse.setCourseName(timeTable.getCourseLab().getShortName());
                    timeTableResponse.setTeacherName(timeTable.getCourseLab().getProfessor().getName());
                }
            }else{
                timeTableResponse.setType(change.getType());
                if(change.getCourse()!=null) {
                    timeTableResponse.setCourseCode(change.getCourse().getCode());
                    timeTableResponse.setCourseName(change.getCourse().getShortName());
                    timeTableResponse.setTeacherName(change.getCourse().getProfessor().getName());
                    timeTableResponse.setLoc(change.getLoc());

                }
                else if(change.getCourseLab() != null){
                    timeTableResponse.setCourseCode(change.getCourseLab().getCode());
                    timeTableResponse.setCourseName(change.getCourseLab().getShortName());
                    timeTableResponse.setTeacherName(change.getCourseLab().getProfessor().getName());
                    timeTableResponse.setLoc(change.getLoc());
                }
            }
            timeTableResponse.setLoc(timeTable.getLoc());
            timeTableResponse.setDay(timeTable.getDay());
            timeTableResponse.setPeriod(timeTable.getPeriod());
            timeTableResponse.setGrp(timeTable.getGrp());
            timeTableResponse.setClassId(timeTable.getClassId());


            response.add(timeTableResponse);
        }

        return response;
    }
}
