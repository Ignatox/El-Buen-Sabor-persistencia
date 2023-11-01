package com.utn.EBS.Enumeraciones;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

@JsonAppend
public enum UnidadMedida {
    GRAMO("Gramo"),
    LITROS("Litros"),
    UNIDADES("Unidades");

    private String texto;

    private UnidadMedida(String texto){
        this.texto=texto;
    }

    public String getTexto(){
        return texto;
    }
}
