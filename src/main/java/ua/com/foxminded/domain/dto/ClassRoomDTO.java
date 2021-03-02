package ua.com.foxminded.domain.dto;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class ClassRoomDTO {
    Integer id;
    @Positive(message = "Incorrect number")
    Integer number;
    @Positive(message = "Incorrect capacity")
    Integer capacity;
}
