package Repositorios;

import Entidades.Producto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends BaseRepository <Producto, Long>{
}
