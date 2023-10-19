package Repositorios;

import Entidades.DetallePedido;
import Entidades.Pedido;
import Entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends BaseRepository<DetallePedido, Long>{

    @Query("SELECT d FROM DetallePedido d WHERE d.cantidad = :cantidad")
    List<Pedido> buscarPorCantidad(@Param("cantidad") int cantidad);

    @Query("SELECT d FROM DetallePedido d WHERE d.cantidad = :cantidad")
    Page<Pedido> buscarPorCantidad(@Param("cantidad") int cantidad, Pageable pageable);

    @Query("SELECT d FROM DetallePedido d WHERE d.Producto = :producto")
    List<Pedido> buscarPorProducto(@Param("producto") Producto producto);

    @Query("SELECT d FROM DetallePedido d WHERE d.Producto = :producto")
    Page<Pedido> buscarPorProducto(@Param("producto") Producto producto, Pageable pageable);
}
