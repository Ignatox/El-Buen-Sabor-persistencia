package com.utn.EBS.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ConfigGeneral")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionGeneral extends BaseEntidad{

    private int cantidadCocineros;
    private String emailEmpresa;
    private String tokenMercadoPago;

    //No tiene relacion con ninguna otra clase

}
