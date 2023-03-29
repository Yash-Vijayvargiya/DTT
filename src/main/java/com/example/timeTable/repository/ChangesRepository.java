package com.example.timeTable.repository;

import com.example.timeTable.model.entities.Change;
import com.example.timeTable.model.entities.TimeTable;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangesRepository extends JpaRepository<Change,Long> {

  Change findChangeByTimeTable(TimeTable timeTable);

  List<Change> getChangesByTimeTableInAndDateOutAfterAndDateInBefore(List<TimeTable> timeTables, Date startDate, Date endDate);

}
