package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.DetallePedido;
import com.utn.EBS.Entidades.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long>{

    @Query("SELECT p FROM Pedido p WHERE p.id = :id")
    Pedido buscarPorId(@Param("id") Long id);

    @Query("SELECT p FROM Pedido p WHERE p.estado = 'A_PREPARAR'")
    List<Pedido> buscarPedidosAPreparar(Pageable pageable);

}
