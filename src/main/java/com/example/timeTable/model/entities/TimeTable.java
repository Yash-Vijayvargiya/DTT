package com.example.timeTable.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class TimeTable {
    @Id
    @SequenceGenerator( name = "time_table_id_sequence",
            sequenceName = "time_table_id_sequence",
            allocationSize = 10)
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
            generator = "time_table_id_sequence")
            @Column(name="time_table_id")
    private Long id;

    private String day;
    private int period;
    private String type;
    private String classId;
    private String grp;
    private String loc;
    @ManyToOne(targetEntity = Course.class)
    private Course course;
    @ManyToOne(targetEntity = CourseLab.class)
    private CourseLab courseLab;


}
