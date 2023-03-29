package com.example.timeTable.controller;

import com.example.timeTable.model.requestModel.ChangeRequest;
import com.example.timeTable.service.ChangesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/professor/")
public class ProfessorController {

  @Autowired
  private ChangesService changesService;

  @PostMapping("/addChange/{branch}/{sem}")
  public String addChange(
      @PathVariable("branch") String branch,
      @PathVariable("sem")String sem,
      @RequestBody ChangeRequest changeRequest){
    changesService.addChange(branch+"_"+sem, changeRequest);
    return "Successfully Done";
  }

}
