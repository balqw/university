package ua.com.foxminded.domain.entity;
import lombok.*;




@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class StudentEntity extends Person{

    private int course;

}
