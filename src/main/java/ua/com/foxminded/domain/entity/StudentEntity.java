package ua.com.foxminded.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

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
