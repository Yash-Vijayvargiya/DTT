package com.example.timeTable.model.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseLabRequest {
    private String name;
    private String code;
    private String grp;
    private String dept;
    private String professorEmail;

}
