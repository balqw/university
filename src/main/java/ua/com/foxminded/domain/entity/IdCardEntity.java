package ua.com.foxminded.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "idCard")
public class IdCardEntity {
    @Id
    @GeneratedValue
    private Integer cardId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateExpire;

}
