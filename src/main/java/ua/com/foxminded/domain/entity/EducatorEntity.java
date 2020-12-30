package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "educator")
public class EducatorEntity extends Person {
    @Id
    @GeneratedValue
    private Integer educatorId;
    @OneToOne
    @JoinColumn(name = "idCard")

    private IdCardEntity idCard;
}
