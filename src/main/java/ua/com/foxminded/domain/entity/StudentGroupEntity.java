package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "student_group")
public class StudentGroupEntity {
    @Id
    @GeneratedValue
    private Integer groupId;
    private String abbreviate;
    private String description;
}
