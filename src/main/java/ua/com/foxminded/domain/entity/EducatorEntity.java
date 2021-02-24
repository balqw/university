package ua.com.foxminded.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "educator")
@EqualsAndHashCode(callSuper = true)
public class EducatorEntity extends Person {
    @Id
    @GeneratedValue
    @Column(name = "educator_id")
    private Integer educatorId;
    @OneToOne
    @JoinColumn(referencedColumnName = "card_id")
    private IdCardEntity idCard;
}
