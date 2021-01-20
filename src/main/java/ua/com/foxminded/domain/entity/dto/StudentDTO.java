package ua.com.foxminded.domain.entity.dto;

import lombok.Data;

@Data
public class StudentDTO {
    Integer id;
    String name;
    String surName;
    Integer course;
}
