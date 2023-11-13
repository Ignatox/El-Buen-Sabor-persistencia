package com.utn.EBS.Repositorios;


import com.utn.EBS.Entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario,Long> {

    @Query("SELECT u FROM Usuario u WHERE u.id = :id")
    Usuario buscarPorId(@Param("id") Long id);
    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    List<Usuario> buscarPornombre(@Param("nombre") String nombre);

    @Query("SELECT u FROM Usuario u WHERE u.rol = :rol")
    List<Usuario> buscarPorRol(@Param("rol") String rol);

    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    Page<Usuario> buscarPornombre(@Param("nombre") String nombre, Pageable pageable);

    @Query("SELECT u FROM Usuario u WHERE u.rol = :rol")
    Page<Usuario> buscarPorRol(@Param("rol") String rol, Pageable pageable);

    Optional<Usuario> BuscarPorNombreUsuario(String nombre); // es casi lo mismo que buscarPornombre

}
