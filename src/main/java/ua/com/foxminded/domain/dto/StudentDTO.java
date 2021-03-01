package ua.com.foxminded.domain.dto;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import ua.com.foxminded.domain.entity.Group;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class StudentDTO {
    private Integer id;
    @NotEmpty
    @Size(min = 2, max = 10, message = "Name must be between 2 and 10 letters")
    private String name;
    @NotEmpty
    @Size(min = 2, max = 10, message = "Surname must be between 2 and 10 letters")
    private String surName;
    @Positive(message = "course must be a number")
    private Integer course;
    private GroupDTO group;
}
