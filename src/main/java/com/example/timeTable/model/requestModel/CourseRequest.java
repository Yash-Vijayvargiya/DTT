package com.example.timeTable.model.requestModel;

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
    private String professorEmail;

}
