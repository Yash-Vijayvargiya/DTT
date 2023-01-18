package com.example.timeTable.model.responseModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfessorResponse {
    private Long profId;
    private String name;
    private String emailId;
}
