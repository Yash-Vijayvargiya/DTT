package com.example.timeTable.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long course_id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String code;
    private String dept;
    private String shortName;


    @JsonIgnore
    @ManyToOne
    private Professor professor;
    @JsonIgnore

    @OneToMany(mappedBy = "course")
    private List<TimeTable> timeTable;
}
