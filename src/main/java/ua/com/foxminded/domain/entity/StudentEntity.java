package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue
    @Column("student_id")
    private Integer studentId;
    @Column("course")
    private Integer course;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    @ManyToOne
    @JoinColumn(referencedColumnName ="group_id")
    private Group group;
}
