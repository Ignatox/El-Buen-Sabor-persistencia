package com.utn.EBS.Repositorios;

import com.utn.EBS.DTO.ProductoPantallaPrincipalDTO;
import com.utn.EBS.DTO.RankingProductoDTO;
import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductoRepository  extends BaseRepository <Producto, Long>{

    @Query("SELECT p FROM Producto p WHERE p.id = :id")
    Producto buscarPorId(@Param("id") Long id);

    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    List<Producto> buscarPorNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    Page<Producto> buscarPorNombre(@Param("nombre") String nombre, Pageable pageable);

    @Query("SELECT new com.utn.EBS.DTO.ProductoPantallaPrincipalDTO(p.id, p.tiempoEstimadoCocina, p.nombre, p.descripcion, p.foto, p.precio) FROM Producto p WHERE p.rubro.id = :idRubro")
    List<ProductoPantallaPrincipalDTO> buscarPorRubroDTO(@Param("idRubro") Long idRubro);

    @Query("SELECT new com.utn.EBS.DTO.RankingProductoDTO(dp.producto.nombre, dp.producto.rubro.tipoRubro, SUM(dp.cantidad)) FROM DetallePedido dp " +
            "WHERE dp.pedido.fecha BETWEEN :fechaDesde AND :fechaHasta " +
            "GROUP BY dp.producto.nombre")
    List<RankingProductoDTO> buscarRankingProductos(@Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);

    @Query("SELECT p.rubro.id FROM Producto p")
    Producto buscarTodo();
}
