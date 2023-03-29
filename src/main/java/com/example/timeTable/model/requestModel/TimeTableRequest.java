package com.example.timeTable.model.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableRequest {
    private int period;
    private String day;
    private String courseCode;
    private String courseName;
    private String type;
    private String teacherName;
    private String grp;
}
