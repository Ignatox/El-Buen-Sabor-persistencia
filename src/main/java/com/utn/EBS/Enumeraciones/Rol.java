package com.utn.EBS.Enumeraciones;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

@JsonAppend
public enum Rol {
    Cajero("Cajero"),
    Delivery("Delivery"),
    Cliente("Cliente"),
    Administrador("Administrador");

    private String texto;

    private Rol(String texto){
        this.texto=texto;
    }

    public String getTexto(){
        return texto;
    }

}
