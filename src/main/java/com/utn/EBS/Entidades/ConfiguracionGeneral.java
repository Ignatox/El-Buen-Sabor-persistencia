package com.utn.EBS.Entidades;

import jakarta.persistence.Column;
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
// todos putos
    @Column(name = "cantidad_cocineros")
    private int cantidadCocineros;
    @Column(name = "email_empresa")
    private String emailEmpresa;
    @Column(name = "token_mp")
    private String tokenMercadoPago;

    //No tiene relacion con ninguna otra clase

}
