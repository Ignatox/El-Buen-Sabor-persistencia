package com.utn.EBS.DTO;

import lombok.Data;

@Data
public class CambiarContraseñaDTO {
    private String emailActual;
    private String contraseñaActual;
    private String nuevaContraseña;
    private String confirmarContraseña;
}
