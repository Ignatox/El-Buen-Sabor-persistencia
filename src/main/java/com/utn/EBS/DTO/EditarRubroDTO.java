package com.utn.EBS.DTO;

import com.utn.EBS.Entidades.Ingrediente;
import com.utn.EBS.Enumeraciones.EstadoRubro;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EditarRubroDTO {
    private Long idRubro;
    private String nombre;
    public EstadoRubro estado;
    //private String NombreIngrediente;
    private List<Ingrediente> IngredienteRel;
    //lista de ingredientes relacionada al rubro
    //private List<NombreIngredienteDTO> NombreIngrediente;


    
}
