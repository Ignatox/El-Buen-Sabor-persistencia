package Repositorios;

import Entidades.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository  extends BaseRepository <Producto, Long>{

    List<Producto> findAllByStock(int stockactual, int stockminimo);
    @Query(value = "SELECT p FROM producto p WHERE p.stockactual <= p.stockminimo")
    List<Producto> productosAreponer();


}
