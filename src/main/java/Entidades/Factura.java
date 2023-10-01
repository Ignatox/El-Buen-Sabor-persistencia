package Entidades;

import Enumeraciones.FormaPago;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name="factura")
@Getter
@Setter
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
