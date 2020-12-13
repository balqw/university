package ua.com.foxminded.domain.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class IdCardEntity {
    private Integer cardId;
    private LocalDateTime dataExpire;
}
