package ua.com.foxminded.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class GroupDTO {
    private Integer groupId;

    @NotEmpty(message = "Can't be empty")
    @Size(min = 2, max = 10, message = "Must be between 2 and 10 letters")
    private String abbreviate;

    private String description;
}
