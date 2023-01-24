package com.example.timeTable.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "student_email_unique", columnNames = "EMAIL_ID")
})
@Data
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "ROLL_NUMBER")
    private Long rollNumber;

    @Column(name="NAME")
    private String name;

    @Column(name="EMAIL_ID")
    private String emailId;

    //classId=branch+"_"+semester
    @Column(name="CLASS_ID")
    private String classID;

    public Student(Long rollNumber, String name, String emailId, String classID) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.emailId = emailId;
        this.classID = classID;
    }


}

