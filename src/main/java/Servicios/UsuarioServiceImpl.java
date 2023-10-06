package Servicios;

import Entidades.Usuario;
import Repositorios.BaseRepository;
import Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {
        super(baseRepository);
    }
}
