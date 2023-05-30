package com.example.timeTable.model.requestModel;

import com.example.timeTable.model.entities.Role;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStudentRequest {
  private String email;
  private String password;
  private Role role;
  private String name;
  private Long rollNumber;
  private String classID;
}
