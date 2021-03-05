package ua.com.foxminded.domain.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ApiDataException {

    String message;
    LocalDateTime time;

    public ApiDataException(String message, LocalDateTime time) {
        this.message = message;
        this.time = time;
    }
}
