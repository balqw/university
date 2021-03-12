package ua.com.foxminded.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.NumberFormat;
import ua.com.foxminded.domain.entity.Group;
import ua.com.foxminded.validation.CustomSizeConstraint;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class StudentDTO {
    private Integer id;

    @CustomSizeConstraint
    private String name;
    @NotEmpty
    @Size(min = 2, max = 10, message = "Surname must be between 2 and 10 letters")
    private String surName;
    @Positive(message = "ddd")
    private Integer course;
    @Valid
    private GroupDTO group;
}
