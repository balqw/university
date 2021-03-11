package ua.com.foxminded.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class GroupDTO {
    private Integer groupId;

    @NotEmpty(message = "Can't be empty")
    @Size(min = 2, max = 10, message = "Must be between 2 and 10 letters")
    private String abbreviate;

    private String description;
}
