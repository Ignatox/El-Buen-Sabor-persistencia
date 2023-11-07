package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.TipoRubro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class RankingProductoDTO {
    String nombreProducto;
    TipoRubro tipoRubro;
    Long cantidadVendida;

    public RankingProductoDTO(String nombreProducto, TipoRubro tipoRubro, Long cantidadVendida) {
        this.nombreProducto = nombreProducto;
        this.tipoRubro = tipoRubro;
        this.cantidadVendida = cantidadVendida;
    }
}
