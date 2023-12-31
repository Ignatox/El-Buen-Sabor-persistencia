package com.utn.EBS.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.utn.EBS.Enumeraciones.EstadoPedido;
import com.utn.EBS.Enumeraciones.FormaPago;
import com.utn.EBS.Enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pedido")
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE pedido SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")
public class Pedido extends BaseEntidad {

    @Column(name = "fecha_pedido", nullable = false)
    private Date fecha;

    @Column(name = "hora_estimada_entrega", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String horaEstimadaEntrega;

    @Column(name = "total", nullable = false)
    private double total;

    @Column(name = "estado_pedido", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @Column(name = "tipo_envio_pedido", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;

    @Column(name = "tipo_pago_pedido", nullable = false)
    @Enumerated(EnumType.STRING)
    private FormaPago tipoPago;

    @JsonBackReference(value = "pedido-cliente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonManagedReference(value = "pedido-detalle-pedido")
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)            //Relacion con DetallePedido
    private List<DetallePedido> detallePedido;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Factura factura;
}
