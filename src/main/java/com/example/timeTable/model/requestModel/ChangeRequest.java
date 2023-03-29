package com.example.timeTable.model.requestModel;

import java.util.Date;
import lombok.Data;

@Data
public class ChangeRequest {
  private int period;
  private String day;
  private String courseCode;
  private String courseName;
  private String type;
  private String grp;
  private Date dateIn;
  private Date dateOut;
}
