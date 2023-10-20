package com.utn.EBS.Entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE cliente SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")
public class Cliente extends BaseEntidad{

    @Column(name = "nombre")
    @NotNull
    @NotEmpty
    private String nombre;

    @Column(name = "apellido")
    @NotNull
    @NotEmpty
    private String apellido;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    @NotNull
    @NotEmpty
    private String email;


    @OneToMany(mappedBy = "cliente",cascade = CascadeType.PERSIST)
    private List<Domicilio> domicilios = new ArrayList<Domicilio>();  //Fijate gonza la navegabilidad, si desde cliente o desde domicilio, como te pinta hacerla

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)         //Relacion con Pedido
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    //Relacion one to one con usuario (foreign key usuario)
    @OneToOne(cascade = CascadeType.ALL)                                                            //Relacion con Factura
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
