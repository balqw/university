package ua.com.foxminded.domain.entity;
import lombok.*;

import javax.persistence.MappedSuperclass;


@Data
@MappedSuperclass
public class Person {
    private String firstName;
    private String lastName;
}

