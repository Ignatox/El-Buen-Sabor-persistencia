package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Entidades.Rubro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubroRepository extends BaseRepository <Rubro, Long>{

    @Query("SELECT r FROM Rubro r WHERE r.id = :id")
    Rubro buscarPorId(@Param("id") Long id);
    @Query("SELECT r FROM Rubro r WHERE r.nombre = :nombre")
    List<Rubro> buscarPorNombre(@Param("nombre") String nombre);


}
