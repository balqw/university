package ua.com.foxminded.domain.entity;
import lombok.Data;
import lombok.NonNull;
import ua.com.foxminded.domain.entity.Person;


@Data
public class StudentEntity extends Person {
    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private int course;
}
