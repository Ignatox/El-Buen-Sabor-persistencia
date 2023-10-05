package Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="detalle_pedido")

public class DetallePedido extends BaseEntidad{

    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "subtotal")
    private double subtotal;

    /* RELACIÓN CON PRODUCTO*/
    @ManyToOne()
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
