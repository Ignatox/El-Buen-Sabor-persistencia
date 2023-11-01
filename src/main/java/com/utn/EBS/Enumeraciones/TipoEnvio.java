package com.utn.EBS.Enumeraciones;

import com.fasterxml.jackson.databind.annotation.JsonAppend;


@JsonAppend
public enum TipoEnvio {
    DELIVERY("Delivery"),
    RETIRA("Retira");

    private String texto;

    private TipoEnvio(String texto){
        this.texto=texto;
    }

    public String getTexto(){
        return texto;
    }
}
