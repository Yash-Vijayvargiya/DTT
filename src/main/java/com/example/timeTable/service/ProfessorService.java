package com.example.timeTable.service;

import com.example.timeTable.model.entities.Professor;
import com.example.timeTable.model.requestModel.ProfessorRequest;
import com.example.timeTable.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    @Autowired
    ProfessorRepository professorRepository;

    public void createProfessor(ProfessorRequest professorRequest){
        Professor professor= new Professor();
       //TODO define mapper class
        professor.setName(professorRequest.getName());
        professor.setDept(professorRequest.getDept());
        professor.setEmailId(professorRequest.getEmailId());
        professor.setPhoneNumber(professorRequest.getPhoneNumber());
        professorRepository.save(professor);
    }
}
