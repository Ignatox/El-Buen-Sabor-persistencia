package com.utn.EBS.Entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="domicilio")
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE domicilio SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")

public class Domicilio extends BaseEntidad{

    @Column(name = "calle")
    @NotNull
    @NotEmpty
    private String calle;

    @Column(name = "numero")
    private String numero;

    @Column(name = "localidad")
    @NotNull
    @NotEmpty
    private String localidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @OneToMany(orphanRemoval = true)                            //Relacion con Pedido
    @JoinColumn(name = "domicilio_id")
    private List<Pedido> pedidos = new ArrayList<>();

}

