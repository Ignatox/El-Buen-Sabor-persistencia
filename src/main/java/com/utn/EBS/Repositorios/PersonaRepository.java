package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long>{

    @Query("SELECT c FROM Cliente c WHERE c.id = :id")
    Persona buscarPorId(@Param("id") Long id);

    @Query ("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    List<Persona> buscarPornombre(@Param("nombre") String nombre);

    @Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre AND c.apellido = :apellido")
    List<Persona> buscarPornombreYApellido(@Param("nombre") String nombre, @Param("apellido") String apellido);

    @Query ("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    Page<Persona> buscarPornombre(@Param("nombre") String nombre, Pageable pageable);

    @Query ("SELECT c FROM Cliente c WHERE c.nombre = :nombre AND c.apellido = :apellido")
    Page<Persona> buscarPornombreYApellido(@Param("nombre") String nombre, @Param("apellido") String apellido, Pageable pageable);

    @Query ("SELECT c FROM Cliente c WHERE c.email = :email")
    Persona buscarPorEmail(@Param("email") String email);

}
