package ua.com.foxminded.domain.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class EducatorDTO {
    private Integer educatorId;

    @NotEmpty(message = "Enter name")
    @Size(min = 2, max = 20, message = "Incorrect name")
    private String name;

    @Size(min = 2, max = 10, message = "Incorrect Surname")
    private String surname;

    @Valid
    private IdCardDTO idCardDTO;
}
