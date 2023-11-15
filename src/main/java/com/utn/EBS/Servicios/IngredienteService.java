package com.utn.EBS.Servicios;
import com.utn.EBS.DTO.IngredienteDTO;
import com.utn.EBS.Entidades.Ingrediente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IngredienteService extends BaseService<Ingrediente,Long>{
    List<Ingrediente> buscarPorStockOK() throws Exception;
    List<Ingrediente> buscarPorStockNoOK() throws Exception;

   // Page<IngredienteDTO> buscarPorStockNoOK(Pageable pageable) throws Exception;

    List<Ingrediente> buscarPorNombre(String filtroNombre) throws Exception;


}
