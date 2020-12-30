package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "classroom")
public class ClassRoomEntity {
    @Id
    @GeneratedValue
    private Integer classId;
    private Integer number;
    private Integer capacity;
}
