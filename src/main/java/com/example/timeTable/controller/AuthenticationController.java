package com.example.timeTable.controller;

import com.example.timeTable.model.requestModel.AuthenticateRequest;
import com.example.timeTable.model.requestModel.RegisterAdminRequest;
import com.example.timeTable.model.requestModel.RegisterProfessorRequest;
import com.example.timeTable.model.requestModel.RegisterStudentRequest;
import com.example.timeTable.model.responseModel.AuthenticationResponse;
import com.example.timeTable.service.AuthenticationService;
import com.example.timeTable.service.ProfessorService;
import com.example.timeTable.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final StudentService studentService;
    private final ProfessorService professorService;

    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterAdminRequest request){
        return ResponseEntity.ok(authenticationService.registerAdmin(request));
    }

    @PostMapping("/registerStudent")
    public ResponseEntity<AuthenticationResponse> registerStudent(@RequestBody RegisterStudentRequest request){
        studentService.createStudent(request);
        return ResponseEntity.ok(authenticationService.registerStudent(request));
    }

    @PostMapping("/registerProfessor")
    public ResponseEntity<AuthenticationResponse> registerProfessor(@RequestBody RegisterProfessorRequest request){
        professorService.createProfessor(request);
        return ResponseEntity.ok(authenticationService.registerProfessor(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));

    }
}
