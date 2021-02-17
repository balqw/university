package ua.com.foxminded.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "educator")
public class EducatorEntity extends Person {
    @Id
    @GeneratedValue
    @Column("educator_id")
    private Integer educatorId;
    @OneToOne
    @JoinColumn(referencedColumnName = "id_card")
    private IdCardEntity idCard;
}
