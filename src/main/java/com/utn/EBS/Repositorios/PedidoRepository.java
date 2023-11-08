package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.DetallePedido;
import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.Rubro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Date;
import java.util.List;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {

    @Query("SELECT p FROM Pedido p WHERE p.id = :id")
    Pedido buscarPorId(@Param("id") Long id);

    @Query("SELECT p FROM Pedido p WHERE p.estado = 'A_PREPARAR'")
    Page<Pedido> buscarPedidosAPreparar(Pageable pageable);
    @Query("SELECT p FROM Pedido p ORDER BY p.fecha")
    Page<Pedido> buscarPorFecha(Pageable pageable);
    List<Pedido> buscarPedidosAPreparar();
    @Query("SELECT p FROM Pedido p WHERE p.fecha > :fechaDesde AND p.fecha < :fechaHasta")
    List<Pedido> buscarEntreFechas(@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);

    @Query("SELECT p FROM Pedido p WHERE p.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Pedido> buscarPedidosEntreFecha(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

    @Query("SELECT p FROM Pedido p Where p.cliente = :id")
    List<Pedido> buscarPorCliente(@Param("id") Long id);  //ESTA MALLLLLLLLL
    List<Pedido> findByCliente(Cliente cliente);
}