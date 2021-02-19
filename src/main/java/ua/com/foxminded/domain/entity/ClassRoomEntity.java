package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "class_room")
public class ClassRoomEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "class_id")
    private Integer classId;
    @Column(name = "number")
    private Integer number;
    @Column(name = "capacity")
    private Integer capacity;

}
