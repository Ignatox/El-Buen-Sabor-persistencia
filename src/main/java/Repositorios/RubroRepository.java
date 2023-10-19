package Repositorios;

import Entidades.Rubro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubroRepository extends BaseRepository <Rubro, Long>{

    @Query("SELECT r FROM Rubro r WHERE r.denominacion = :denominacion")
    List<Rubro> buscarPorDenominacion(@Param("denominacion") String denominacion);

    @Query("SELECT r FROM Rubro r WHERE r.productos.denominacion = :denominacionProducto")
    Rubro buscarPorDenominacionProducto(@Param("denominacionProducto") String denominacionProducto);
}
