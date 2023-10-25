package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Enumeraciones.TipoProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository  extends BaseRepository <Producto, Long>{
    @Query("SELECT p FROM Producto p WHERE p.denominacion = :denominacion")
    List<Producto> buscarPorDenominacion(@Param("denominacion") int denominacion);

    @Query("SELECT p FROM Producto p WHERE p.tipoProducto = :tipoProducto")
    List<Producto>  buscarPorTipoProducto(@Param("tipoProducto") TipoProducto tipoProducto);

    @Query("SELECT p FROM Producto p WHERE p.denominacion = :denominacion")
    Page<Producto> buscarPorDenominacion(@Param("denominacion") int denominacion, Pageable pageable);

    @Query("SELECT p FROM Producto p WHERE p.tipoProducto = :tipoProducto")
    Page<Producto> buscarPorTipoProducto(@Param("tipoProducto") TipoProducto tipoProducto, Pageable pageable);


    @Query("SELECT p FROM Producto p WHERE p.stockActual <= p.stockMinimo")
    List<Producto> reponerProducto();

    @Query("SELECT p FROM Producto p WHERE p.stockActual <= p.stockMinimo")
    Page<Producto> reponerProducto(Pageable pageable);
}
