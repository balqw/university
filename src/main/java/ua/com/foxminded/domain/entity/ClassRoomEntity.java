package ua.com.foxminded.domain.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class ClassRoomEntity {
    private int classId;
    private int number;
    private int capacity;
}
