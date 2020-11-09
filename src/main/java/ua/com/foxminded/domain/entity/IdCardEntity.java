package ua.com.foxminded.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class IdCardEntity {

    private int id;
    private LocalDateTime dataExpire;
}
