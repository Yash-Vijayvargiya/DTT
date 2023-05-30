package com.example.timeTable.model.responseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private String code;
    private String name;
    private String dept;
    private String shortName;
    private String grp;
}
