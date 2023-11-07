package com.utn.EBS.DTO;

import lombok.Data;

@Data
public class MovimientoMonetarioDTO {
    int numeroPedido; //ID DEL PEDIDO
    String nombreCliente; // NOMBRE DEL Cliente
    double costo; // COSTO DE INGREDIENTE
    double total; //INGRESO

}
