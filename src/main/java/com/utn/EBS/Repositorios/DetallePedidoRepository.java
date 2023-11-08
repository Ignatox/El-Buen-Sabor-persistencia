package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.DetallePedido;
import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido, Long>{

    @Query("SELECT d FROM DetallePedido d WHERE d.id = :id")
    DetallePedido buscarPorId(@Param("id") Long id);
    @Query("SELECT d FROM DetallePedido d WHERE d.cantidad = :cantidad")
    List<DetallePedido> buscarPorCantidad(@Param("cantidad") int cantidad);

    @Query("SELECT d FROM DetallePedido d WHERE d.cantidad = :cantidad")
    Page<DetallePedido> buscarPorCantidad(@Param("cantidad") int cantidad, Pageable pageable);

    @Query("SELECT d FROM DetallePedido d WHERE d.producto = :producto")
    List<DetallePedido> buscarPorProducto(@Param("producto") Producto producto);

    @Query("SELECT d FROM DetallePedido d WHERE d.producto = :producto")
    Page<Pedido> buscarPorProducto(@Param("producto") Producto producto, Pageable pageable);
}
