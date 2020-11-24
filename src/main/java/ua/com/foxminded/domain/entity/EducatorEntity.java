package ua.com.foxminded.domain.entity;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
public class EducatorEntity extends Person{
    private int educatorId;
    private IdCardEntity idCard;
}
