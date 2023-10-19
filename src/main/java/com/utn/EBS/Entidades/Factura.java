package com.utn.EBS.Entidades;

import com.utn.EBS.Enumeraciones.FormaPago;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="factura")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Factura extends BaseEntidad {

    @Column (name = "nroFactura")
    private int numero;

    @Column (name = "fechaFactura")
    private Date fecha;

    @Column (name = "dtoFactura")
    private double descuento;

    @Column (name = "formaPago")
    private FormaPago formaPago;

    @Column (name = "total")
    private int total;
}
