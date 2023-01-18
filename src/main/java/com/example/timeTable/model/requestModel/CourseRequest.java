package com.example.timeTable.model.requestModel;

import com.example.timeTable.model.responseModel.ProfessorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
    private String name;
    private String code;
    private String dept;

    private ProfessorRequest professorRequest;

    public CourseRequest(String name, String code, String dept, String profName, String emailId) {
        this.name = name;
        this.code = code;
        this.dept = dept;
        this.professorRequest = new ProfessorRequest(profName,null,emailId,null);
    }
}
