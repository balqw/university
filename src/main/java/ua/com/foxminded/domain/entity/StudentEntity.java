package ua.com.foxminded.domain.entity;
import lombok.*;
import org.springframework.stereotype.Component;


@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Component
public class StudentEntity extends Person{
    private int studentId;
    private int course;
}
