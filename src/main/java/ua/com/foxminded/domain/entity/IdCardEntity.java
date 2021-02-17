package ua.com.foxminded.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "id_card")
public class IdCardEntity {
    @Id
    @GeneratedValue
    @Column(name = "card_id")
    private Integer cardId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "date_expire")
    private LocalDate dateExpire;

}
