package com.example.timeTable.service;

import com.example.timeTable.model.entities.*;
import com.example.timeTable.model.requestModel.RegisterProfessorRequest;
import com.example.timeTable.model.responseModel.ProfessorResponse;
import com.example.timeTable.model.responseModel.TimeTableResponse;
import com.example.timeTable.repository.ChangesRepository;
import com.example.timeTable.repository.ProfessorRepository;
import com.example.timeTable.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    private TimeTableRepository timeTableRepository;
    @Autowired
    private ChangesRepository changesRepository;

    public void createProfessor(RegisterProfessorRequest request){
        Professor professor= new Professor();
       //TODO define mapper class
        professor.setName(request.getName());
        professor.setDept(request.getDept());
        professor.setEmailId(request.getEmail());
        professor.setPhoneNumber(request.getPhoneNumber());
        professorRepository.save(professor);
    }
    public Professor getProfessor(String email){
        return professorRepository.findByEmailId(email);
    }

    public List<ProfessorResponse> getAllProfessor() {
        List<Professor> professors = professorRepository.findAll();
        List<ProfessorResponse> professorResponses = new ArrayList<>();
        for (Professor professor : professors) {
            professorResponses.add(new ProfessorResponse(professor.getProfId(), professor.getName(), professor.getEmailId()));
        }
        return professorResponses;
    }

    public List<TimeTableResponse> getProfTimeTable(String profEmail) {
        Professor professor= professorRepository.findByEmailId(profEmail);
        List<TimeTableResponse> response= new ArrayList<>();
        List<TimeTable> timeTables=timeTableRepository.findByCourseInOrCourseLabIn(professor.getCourses(),professor.getCourseLabs());
        LocalDate date = LocalDate.now();
        String day = String.valueOf(date.getDayOfWeek());
        List<String> days = List.of("Mon", "Tues", "Wed", "Thur", "Fri");
        int weekDay = (days.indexOf(day)+7)%7;
        LocalDate startDate = date.minusDays(weekDay);
        LocalDate endDate = date.plusDays(7-weekDay);

        List<Change> changes = changesRepository.getChangesByTimeTableInAndDateOutAfterAndDateInBefore(timeTables, Date.valueOf(startDate), Date.valueOf(endDate));

        Map<Long, Change> timeTableChange = new HashMap<>();

        for(Change change: changes){
            timeTableChange.put(change.getTimeTable().getId(), change);
        }

        for(TimeTable timeTable: timeTables){
            TimeTableResponse timeTableResponse=new TimeTableResponse();

            Change change = timeTableChange.get(timeTable.getId());

            if(change == null){
                timeTableResponse.setType(timeTable.getType());
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
            }else{
                timeTableResponse.setType(change.getType());
                if(change.getCourse()!=null) {
                    timeTableResponse.setCourseCode(change.getCourse().getCode());
                    timeTableResponse.setCourseName(change.getCourse().getShortName());
                    timeTableResponse.setTeacherName(change.getCourse().getProfessor().getName());
                }
                else if(change.getCourseLab() != null){
                    timeTableResponse.setCourseCode(change.getCourseLab().getCode());
                    timeTableResponse.setCourseName(change.getCourseLab().getShortName());
                    timeTableResponse.setTeacherName(change.getCourseLab().getProfessor().getName());
                }
            }
            timeTableResponse.setDay(timeTable.getDay());
            timeTableResponse.setPeriod(timeTable.getPeriod());
            timeTableResponse.setGrp(timeTable.getGrp());
            timeTableResponse.setClassId(timeTable.getClassId());
            response.add(timeTableResponse);
        }

        return response;

    }
}
