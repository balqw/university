package ua.com.foxminded.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentEntity extends Person {
    private Integer studentId;
    private Integer course;
}
