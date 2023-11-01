package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long>{

    //@Query(SELECT p FROM Pedido p WHERE )

}
