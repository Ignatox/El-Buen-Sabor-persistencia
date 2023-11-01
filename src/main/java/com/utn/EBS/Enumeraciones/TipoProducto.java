package com.utn.EBS.Enumeraciones;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

@JsonAppend
public enum TipoProducto {
    MANUFACTURADO("Manufacturado"),
    INSUMO("Insumo");

    private String texto;

    private TipoProducto(String texto){
        this.texto=texto;
    }

    public String getTexto(){
        return texto;
    }
}
