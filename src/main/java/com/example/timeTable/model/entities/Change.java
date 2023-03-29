package com.example.timeTable.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Change {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = TimeTable.class)
    @JoinColumn(
            name = "time_table_id",
            referencedColumnName = "time_table_id",
            foreignKey = @ForeignKey(
                    name = "time_table_id_fk"
            )
    )
    private TimeTable timeTable;

    @Column(name="date_in")
    private Date dateIn;

    @Column(name = "date_out")
    private Date dateOut;

    @ManyToOne(targetEntity = Course.class)
    private Course course;

    @ManyToOne(targetEntity = CourseLab.class)
    private CourseLab courseLab;

    private String type;
}
