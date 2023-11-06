package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository  extends BaseRepository <Producto, Long>{

    @Query("SELECT p FROM Producto p WHERE p.id = :id")
    Producto buscarPorId(@Param("id") Long id);

    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    List<Producto> buscarPorNombre(@Param("nombre") String nombre);


    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    Page<Producto> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);

    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    List<Producto> buscarPorFechaOrdenadosPorCantidad(@Param("nombre") String nombre, Pageable pageable);
}
