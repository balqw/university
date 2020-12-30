package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "classroom")
public class ClassRoomEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer classId;
    private Integer number;
    private Integer capacity;
}
