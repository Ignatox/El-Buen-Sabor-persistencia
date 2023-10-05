package Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends BaseEntidad{

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;


    @OneToMany(mappedBy = "cliente",cascade = CascadeType.PERSIST)
    private List<Domicilio> domicilios = new ArrayList<Domicilio>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    private List<Pedido> pedidos = new ArrayList<Pedido>();

}
