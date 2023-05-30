package com.example.timeTable.model.responseModel;

import com.example.timeTable.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String userName;
    private String classId;
    private Role role;
    private String token;
    private String emailId;
}
