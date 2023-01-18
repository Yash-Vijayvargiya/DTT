package com.example.timeTable.model.responseModel;

import lombok.Data;

@Data
public class TimeTableResponse {
    private int period;
    private String day;
    private String courseCode;
    private String courseName;
    private String type;

    private String teacherName;
    private String grp;

}
