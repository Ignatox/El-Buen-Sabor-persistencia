package com.utn.EBS.Enumeraciones;

import com.fasterxml.jackson.databind.annotation.JsonAppend;

@JsonAppend
public enum FormaPago {
    EFECTIVO("Efectivo"),
    MERCADO_PAGO("Mercado pago"),
    TARJETA("Tarjeta") ;        //Probablemente haya que agregar más

    private String texto;

    private FormaPago(String texto){
        this.texto=texto;
    }

    public String getTexto(){
        return texto;
    }
}
