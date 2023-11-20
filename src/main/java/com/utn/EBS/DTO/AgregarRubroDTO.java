package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.EstadoRubro;
import com.utn.EBS.Enumeraciones.TipoRubro;
import lombok.Data;

@Data
public class AgregarRubroDTO {
    Long idRubro;
    String nombre;
    TipoRubro tipoRubro;
    EstadoRubro estado;
}
