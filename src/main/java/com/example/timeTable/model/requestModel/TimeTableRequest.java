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
    private String type;
    private String grp;
    private String loc;
}
