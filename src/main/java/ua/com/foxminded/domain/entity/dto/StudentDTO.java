package ua.com.foxminded.domain.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class StudentDTO {
    private Integer id;
    private String name;
    private String surName;
    private Integer course;
    private String studentGroup;
}
