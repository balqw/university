package ua.com.foxminded.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class IdCardEntity {

    private int cardId;
    private LocalDateTime dataExpire;
}
