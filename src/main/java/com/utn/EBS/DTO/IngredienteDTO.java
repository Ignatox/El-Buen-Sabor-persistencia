package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.UnidadMedida;

public class IngredienteDTO {
    Long idIngrediente;
    String nombre;
    Double costo;
    int stockActual;

    int stockMinimo;

    UnidadMedida unidadMedida;
}
