package Entidades;

import Enumeraciones.EstadoPedido;
import Enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pedido")

public class Pedido extends BaseEntidad {

    @Column(name = "fechaPedido")
    private String fecha;

    @Column(name = "horaestimadaentrega")
    @Temporal(TemporalType.TIMESTAMP)
    private String horaEstimadaEntrega;

    @Column(name = "total")
    private double total;

    @NotNull
    @Column(name = "estado_pedido")
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @NotNull
    @Column(name = "tipoenvio_pedido")
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)            //Relacion con DetallePedido
    @JoinTable(
            name = "pedido_detalle",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "detallePedido_id")
    )
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)                                                            //Relacion con Factura
    @JoinColumn(name = "pedido_id")
    private Factura factura;
}
