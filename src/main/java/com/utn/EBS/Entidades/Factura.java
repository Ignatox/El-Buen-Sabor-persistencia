package com.utn.EBS.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utn.EBS.Enumeraciones.FormaPago;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Table(name="factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE factura SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")
public class Factura extends BaseEntidad {

    @Column (name = "nro_factura", nullable = false)
    private int numero;

    @Column (name = "fecha_factura", nullable = false)
    private Date fecha;

    @Column (name = "dto_factura")
    private double descuento;

    @Enumerated(EnumType.STRING)
    @Column (name = "forma_pago", nullable = false)
    private FormaPago formaPago;

    @Column (name = "total", nullable = false)
    private int total;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "factura")
    private Pedido pedido;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "factura_id", referencedColumnName = "id")
    private NotaCredito notaCredito;                                // Para mi es mejor crear la nota de Credito directamente desde Factura
                                                                    // Pq sino hay q hacer un DTO de notaCredito
}
