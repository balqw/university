package ua.com.foxminded.domain.entity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Data
@MappedSuperclass
public class Person {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}


