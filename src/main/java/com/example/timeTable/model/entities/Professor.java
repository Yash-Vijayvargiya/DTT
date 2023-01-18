package com.example.timeTable.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name="professor_email_unique",columnNames = "email_id")})
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Professor {
    @Id
    @Column(name = "prof_id")
    @GeneratedValue()
    private Long profId;
    private String name;
    private String dept;
   @Column(name="email_id")
   private String emailId;
   @Column(name="phone_number")
   private String phoneNumber;
   @OneToMany(mappedBy = "professor")
   private List<Course> courses;
   @OneToMany(mappedBy = "professor")
   private List<CourseLab>courseLabs;
    public Professor(Long profId, String name, String dept, String emailId, String phoneNumber) {
        this.profId = profId;
        this.name = name;
        this.dept = dept;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.courses=null;
    }

    public Professor(Long profId, String name, String dept, String emailId, String phoneNumber, List<Course> courses) {
        this.profId = profId;
        this.name = name;
        this.dept = dept;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "profId=" + profId +
                ", name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
