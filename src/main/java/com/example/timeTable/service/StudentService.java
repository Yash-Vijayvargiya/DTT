package com.example.timeTable.service;

import com.example.timeTable.model.entities.Student;
import com.example.timeTable.model.requestModel.RegisterStudentRequest;
import com.example.timeTable.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  @Autowired
  StudentRepository studentRepository;

  public void createStudent(RegisterStudentRequest request){
    Student student = new Student();
    //TODO define mapper class
    student.setName(request.getName());
    student.setRollNumber(request.getRollNumber());
    student.setEmailId(request.getEmail());
    student.setClassID(request.getClassID());
    studentRepository.save(student);
  }
  public Student getStudentByEmail(String email){
    return studentRepository.findByEmailId(email);
  }
}
