package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.EstadoRubro;
import lombok.Data;

@Data
public class AgregarRubroDTO {
    String nombre;
    EstadoRubro estado;
}
