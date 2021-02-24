package ua.com.foxminded.domain.dto;

import lombok.Data;
import ua.com.foxminded.domain.entity.Group;

@Data
public class StudentDTO {
    private Integer id;
    private String name;
    private String surName;
    private Integer course;
    private Group group;
}
