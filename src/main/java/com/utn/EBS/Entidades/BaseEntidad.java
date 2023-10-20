package com.utn.EBS.Entidades;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Columna para la fecha de alta
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date fecha_alta;

    // Columna para la fecha de modificación
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date fecha_modificacion;
    // Boolean para baja logica
    private boolean deleted = Boolean.FALSE;



}