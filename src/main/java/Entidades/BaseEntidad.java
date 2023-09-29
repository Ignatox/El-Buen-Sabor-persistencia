package Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntidad implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
}