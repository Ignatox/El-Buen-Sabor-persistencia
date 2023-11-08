package com.utn.EBS.DTO;

import com.utn.EBS.Entidades.Ingrediente;
import com.utn.EBS.Enumeraciones.EstadoRubro;
import lombok.Data;

@Data

public class AltaRubroDTO {
    private String nombre;
    public EstadoRubro estado;
    private String NombreIngrediente;
    
}
