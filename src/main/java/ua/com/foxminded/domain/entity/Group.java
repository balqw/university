package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student_group")
public class Group {
    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "abbreviate")
    private String abbreviate;
    @Column(name = "description")
    private String description;
}
