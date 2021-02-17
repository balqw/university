package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "classroom")
public class ClassRoomEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column("class_id")
    private Integer classId;
    private Integer number;
    private Integer capacity;

}
