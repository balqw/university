package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student_group")
public class Group {
    @Id
    @GeneratedValue
    @Column("group_id")
    private Integer groupId;
    @Column("abbreviate")
    private String abbreviate;
    @Column("description")
    private String description;
}
