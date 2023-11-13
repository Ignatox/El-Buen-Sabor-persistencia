package com.utn.EBS.Enumeraciones;
import com.fasterxml.jackson.databind.annotation.JsonAppend;

import java.util.EnumSet;
import java.util.Set;

@JsonAppend
public enum EstadoPedido {  //seguramente hay que agregar mas estados apra tener coherencia
    A_CONFIRMAR("A confirmar"),
    A_PREPARAR("A preparar"),
    INICIADO("iniciado"),
    //había un estado "en preparación" pero lo borre pq es lo mismo que iniciado
    ENTREGADO("Entregado"),
    EN_CAMINO("En camino") ;//para indicar que el delivery lo está llevando

    private String texto;

    private EstadoPedido(String texto){
        this.texto=texto;
    }

    public String getTexto(){
        return texto;
    }
}