package com.example.timeTable.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class CourseLab {
    @Id
    @GeneratedValue
    @Column(name="course_lab_id")
    Long Id;

    String name;
    String code;
    String grp;
    String dept;
    @ManyToOne
    private Professor professor;
}
