package ua.com.foxminded.domain.entity;
import lombok.*;




@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class StudentEntity{

    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private int course;

}
