package ua.com.foxminded.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "student")
public class StudentEntity{
    @Id
    @GeneratedValue
    private Integer studentId;
    private Integer course;
    private String firstName;
    private String lastName;
    private String studentGroup;
}
