package ua.com.foxminded.domain.entity;

import lombok.Data;

@Data
public class EducatorEntity extends Person {
    private Integer educatorId;
    private IdCardEntity idCard;
}
