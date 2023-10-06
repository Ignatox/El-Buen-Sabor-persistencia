package Entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
@Table(name="domicilio")


public class Domicilio extends BaseEntidad{

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero")
    private String numero;

    @Column(name = "localidad")
    private String localidad;


    @OneToMany(orphanRemoval = true)                            //Relacion con Pedido
    @JoinColumn(name = "domicilio_id")
    private List<Pedido> pedidos = new ArrayList<>();

}

