package com.example.timeTable.model.requestModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfessorRequest {
    private String name;
    private String dept;
    private String emailId;
    private String phoneNumber;

}
