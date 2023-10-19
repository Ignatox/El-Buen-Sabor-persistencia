package com.utn.EBS.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cliente")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Cliente extends Usuario{

    @Column(name = "telefono")
    private String telefono;

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.PERSIST)
    private List<Domicilio> domicilios = new ArrayList<Domicilio>();  //Fijate gonza la navegabilidad, si desde cliente o desde domicilio, como te pinta hacerla

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)         //Relacion con Pedido
    private List<Pedido> pedidos = new ArrayList<Pedido>();

}
