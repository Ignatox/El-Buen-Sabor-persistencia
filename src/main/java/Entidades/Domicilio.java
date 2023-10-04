package Entidades;

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
@Table(name="domicilio")

public class Domicilio {
    private String calle;
    private String numero;
    private String localidad;

    @ManyToOne()
    private Cliente cliente;
}
