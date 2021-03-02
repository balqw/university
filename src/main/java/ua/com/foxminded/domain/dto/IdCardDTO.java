package ua.com.foxminded.domain.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class IdCardDTO {
    @Positive
    private Integer cardId;

    @Future(message = "Incorrect date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateExpire;
}
