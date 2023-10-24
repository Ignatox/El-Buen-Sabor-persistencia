package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Factura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends BaseRepository <Factura, Long> {
    @Query("SELECT f FROM Factura f WHERE f.numero = :numero")
    Factura buscarPorNumero(@Param("numero") int numero);

    @Query("SELECT f FROM Factura f WHERE f.fecha = :fecha")
    List<Factura> buscarPorFecha(@Param("fecha") int fecha);

    @Query("SELECT f FROM Factura f WHERE f.fecha = :fecha")
    Page<Factura> buscarPorFecha(@Param("fecha") int fecha, Pageable pageable);
}
