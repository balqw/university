package ua.com.foxminded.domain.dto;

import lombok.Data;

@Data
public class EducatorDTO {
    private Integer educatorId;
    private String name;
    private String surname;
    private IdCardDTO idCardDTO;
}
