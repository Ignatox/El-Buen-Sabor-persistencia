package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Ingrediente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "SELECT i FROM Ingrediente i WHERE stockActual < stockMinimo")
    Page<Ingrediente> buscarPorStockNoOK(Pageable pageable);

    @Query(value = "SELECT i FROM Ingrediente i WHERE i.nombre LIKE '%?filtroNombre%' ")
    List<Ingrediente> buscarPorNombre(@Param("filtroNombre") String filtroNombre);
}