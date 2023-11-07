package com.utn.EBS.Servicios;
import com.utn.EBS.Entidades.Ingrediente;

import java.util.List;

public interface IngredienteService extends BaseService<Ingrediente,Long>{
    List<Ingrediente> buscarPorStockOK() throws Exception;
    List<Ingrediente> buscarPorStockNoOK() throws Exception;
    List<Ingrediente> buscarPorNombre(String filtroNombre) throws Exception;
}
