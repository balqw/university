package ua.com.foxminded.domain.entity.dto;

import lombok.Builder;
import lombok.Data;
import ua.com.foxminded.domain.entity.LessonEntity;

import java.util.List;

@Data
@Builder
public class StudentSummaryInfo {
    private Integer id;
    private String name;
    private String surName;
    private List<LessonEntity> lessons;
    private Integer course;
}
