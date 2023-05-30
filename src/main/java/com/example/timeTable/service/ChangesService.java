package com.example.timeTable.service;

import com.example.timeTable.model.entities.Change;
import com.example.timeTable.model.entities.TimeTable;
import com.example.timeTable.model.requestModel.ChangeRequest;
import com.example.timeTable.repository.ChangesRepository;
import com.example.timeTable.repository.CourseLabRepository;
import com.example.timeTable.repository.CourseRepository;
import com.example.timeTable.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangesService {

  @Autowired
  private ChangesRepository changesRepository;
  @Autowired
  private CourseLabRepository courseLabRepository;
  @Autowired
  private CourseRepository courseRepository;
  @Autowired
  private TimeTableRepository timeTableRepository;

  public void addChange(String classId, ChangeRequest changeRequest){
    System.out.println(changeRequest.toString());

    TimeTable timeTable = timeTableRepository.findTimeTableByPeriodAndGrpAndDayAndClassId(
      changeRequest.getPeriod(),
      changeRequest.getGrp(),
      changeRequest.getDay(),
      classId
    );

    Change change = changesRepository.findChangeByTimeTable(timeTable);

    if(change == null)
      change = new Change();

    change.setTimeTable(timeTable);
    change.setDateIn(changeRequest.getDateIn());
    change.setDateOut(changeRequest.getDateOut());
    change.setType(changeRequest.getType());

    if(changeRequest.getType()!=null && (changeRequest.getType().equalsIgnoreCase("class") || changeRequest.getType().equalsIgnoreCase("tut")))
      change.setCourse(courseRepository.findByCode(changeRequest.getCourseCode()));
    else if(changeRequest.getType()!=null)
      change.setCourseLab(courseLabRepository.findByCodeAndGrp(changeRequest.getCourseCode(),changeRequest.getGrp()));

    changesRepository.save(change);
  }
}
