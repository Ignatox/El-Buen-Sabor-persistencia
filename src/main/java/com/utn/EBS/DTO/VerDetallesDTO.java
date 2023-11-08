package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.EstadoPedido;
import com.utn.EBS.Enumeraciones.FormaPago;
import com.utn.EBS.Enumeraciones.TipoEnvio;
import lombok.Data;

@Data
public class VerDetallesDTO {
    //private ... Fecha
    private EstadoPedido Estado;
    private String Cliente; //haciendo referencia al nombre y apellido
    private int Telefono;
    private String Direccion;
    private String Departamento;
    private TipoEnvio formaEntrega;
    private FormaPago TipoPago;
    //private ... hora;
}
