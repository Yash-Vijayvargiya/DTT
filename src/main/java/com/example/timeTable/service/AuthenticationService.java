package com.example.timeTable.service;

import com.example.timeTable.config.JwtService;
import com.example.timeTable.model.entities.Professor;
import com.example.timeTable.model.entities.Role;
import com.example.timeTable.model.entities.Student;
import com.example.timeTable.model.entities.User;
import com.example.timeTable.model.requestModel.AuthenticateRequest;
import com.example.timeTable.model.requestModel.RegisterAdminRequest;
import com.example.timeTable.model.requestModel.RegisterProfessorRequest;
import com.example.timeTable.model.requestModel.RegisterStudentRequest;
import com.example.timeTable.model.responseModel.AuthenticationResponse;
import com.example.timeTable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StudentService studentService;

    public AuthenticationResponse registerAdmin(RegisterAdminRequest request) {
        var user= User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
                .userName(request.getUserName())
            .build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).userName(request.getUserName()).role(Role.Admin).build();
    }

    public AuthenticationResponse registerStudent(RegisterStudentRequest request) {
        var user= User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .userName(request.getName())
            .build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse registerProfessor(RegisterProfessorRequest request) {
        var user= User.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .userName(request.getName())
            .build();
        userRepository.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        var auth= new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        authenticationManager.authenticate(
          auth
        );
        var user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        AuthenticationResponse authenticationResponse;
        if(user.getRole()== Role.Student) {
            Student student = studentService.getStudentByEmail(user.getEmail());
            authenticationResponse= AuthenticationResponse.builder().token(jwtToken).userName(student.getName()).role(Role.Student).classId(student.getClassID()).emailId(student.getEmailId()).build();
        }
        else{
            authenticationResponse = AuthenticationResponse.builder().token(jwtToken).userName(user.getName()).role(user.getRole()).emailId(user.getEmail()).build();
        }
        return authenticationResponse;
    }
}
