package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario,Long> {

    Optional<Usuario> findByEmailAndContrasena(String email, String contrasena);
    Optional<Usuario> findByEmail(String email);
}
