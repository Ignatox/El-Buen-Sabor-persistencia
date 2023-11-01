package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.DetallePedido;
import com.utn.EBS.Entidades.Ingrediente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends BaseRepository <Ingrediente, Long>{

    @Query("SELECT i FROM Ingrediente i WHERE i.id = :id")
    Ingrediente buscarPorId(@Param("id") Long id);

    @Query(value = "SELECT i FROM Ingrediente i WHERE stockActual > stockMinimo")
    List<Ingrediente> buscarPorStockOK();

    @Query(value = "SELECT i FROM Ingrediente i WHERE stockActual < stockMinimo")
    List<Ingrediente> buscarPorStockNoOK();

    @Query(value = "SELECT i FROM Ingrediente i WHERE i.denominacion LIKE '%?filtroDenom%' ")
    List<Ingrediente> buscarPorDenominacion(@Param("filtroDenom") String filtroDenom);
}
