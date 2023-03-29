package com.example.timeTable.repository;

import com.example.timeTable.model.entities.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable,Long> {
    List<TimeTable> findByClassIdOrderByPeriodAsc(String classId);

    TimeTable findTimeTableByPeriodAndGrpAndDayAndClassId(int period, String group, String day, String classId);

    List<TimeTable> getTimeTableByClassIdAndGrpInOrderByPeriodAsc(String classId, List<String> grps);
}
