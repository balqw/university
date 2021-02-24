package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "course")
    private Integer course;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id",referencedColumnName = "group_id")
    private Group group;
}
