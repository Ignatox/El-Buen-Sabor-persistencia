package Repositorios;

import Entidades.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository  extends BaseRepository <Producto, Long>{

    List<Producto> findAllByStock(int stockactual, int stockminimo);
    @Query(value = "SELECT p FROM Producto p WHERE p.stockactual <= p.stockminimo + :unidades")
    List<Producto> findProductosCercanosAReponer(@Param("unidades") int unidades);


}
