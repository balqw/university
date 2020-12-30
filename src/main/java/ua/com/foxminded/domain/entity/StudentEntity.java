package ua.com.foxminded.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "student")
public class StudentEntity extends Person {
    @Id
    @GeneratedValue
    private Integer studentId;
    private Integer course;
}
