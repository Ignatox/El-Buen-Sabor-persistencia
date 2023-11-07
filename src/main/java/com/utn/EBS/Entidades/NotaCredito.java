package com.utn.EBS.Entidades;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="NotaCredito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotaCredito extends BaseEntidad{
    private double disminucion;
    private String motivo;
    private double total;

    //No esta terminada
}
