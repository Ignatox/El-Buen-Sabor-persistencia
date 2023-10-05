package Entidades;

import Enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="usuario")
public class Usuario extends  BaseEntidad {

    @Column(name="nombre")
    private String nombre;
    @Column(name="contraseña")
    private String password;
    @Column(name="rol")
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JoinColumn(name="usuario_id")
    private List<Pedido> Pedidos = new ArrayList<Pedido>();

    public void agregarPedido(Pedido pedido){
        Pedidos.add(pedido);
    }
    public void mostrarPedidos(){
        System.out.println("Pedidos de " + Usuario.this);
        for(Pedido p:Pedidos){
            System.out.println("");
        }

    }

}
