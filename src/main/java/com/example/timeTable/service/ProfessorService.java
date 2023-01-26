package com.example.timeTable.service;

import com.example.timeTable.model.entities.Professor;
import com.example.timeTable.model.requestModel.RegisterProfessorRequest;
import com.example.timeTable.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    public void createProfessor(RegisterProfessorRequest request){
        Professor professor= new Professor();
       //TODO define mapper class
        professor.setName(request.getName());
        professor.setDept(request.getDept());
        professor.setEmailId(request.getEmail());
        professor.setPhoneNumber(request.getPhoneNumber());
        professorRepository.save(professor);
    }
}
