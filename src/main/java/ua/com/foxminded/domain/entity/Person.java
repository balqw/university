package ua.com.foxminded.domain.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Person {

    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
}

