package Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="detallepedido")

public class DetallePedido extends BaseEntidad{
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "subtotal")
    private double subtotal;
}
