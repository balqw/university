package ua.com.foxminded.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data

public class IdCardEntity {
    private Integer cardId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataExpire;
}
