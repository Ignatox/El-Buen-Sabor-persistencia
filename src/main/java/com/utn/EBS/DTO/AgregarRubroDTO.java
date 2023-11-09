package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.EstadoRubro;
import com.utn.EBS.Enumeraciones.TipoRubro;
import lombok.Data;

@Data
public class AgregarRubroDTO {
    String nombre;
    TipoRubro tipoRubro;
    EstadoRubro estado;
}
