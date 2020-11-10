package ua.com.foxminded.domain.entity;

import lombok.Data;

@Data
public class EducatorEntity extends Person{
    private int educatorId;
    private IdCardEntity idCard;
}
