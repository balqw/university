package ua.com.foxminded.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class IdCardEntity {
    private Integer cardId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataExpire;
}
