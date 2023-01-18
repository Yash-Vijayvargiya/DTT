package com.example.timeTable.model.requestModel;

import lombok.Data;

@Data
public class CourseLabRequest {
    private String name;
    private String code;
    private String grp;
    private String dept;

    private ProfessorRequest professorRequest;

    public CourseLabRequest(String name, String code, String grp, String dept, String profName, String emailId) {
        this.name = name;
        this.code = code;
        this.grp = grp;
        this.dept = dept;
        this.professorRequest = new ProfessorRequest(profName,"",emailId,"");

    }
}
