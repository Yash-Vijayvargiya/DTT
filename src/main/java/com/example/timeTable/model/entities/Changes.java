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
public class Changes {
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
    private Boolean type;

    public Changes(Long id, TimeTable timeTable, Date dateIn, Date dateOut, Boolean type) {
        this.id = id;
        this.timeTable = timeTable;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.type = type;
    }
}
