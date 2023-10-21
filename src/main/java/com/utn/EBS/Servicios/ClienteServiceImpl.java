package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.RegistroClienteDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Enumeraciones.Rol;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.ClienteRepository;
import com.utn.EBS.Repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long>
implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public Cliente registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception {
        try{
            Usuario usuario = new Usuario(
                    registroClienteDTO.getNombre(),
                    registroClienteDTO.getApellido(),
                    registroClienteDTO.getEmail(),
                    registroClienteDTO.getContrasena(),
                    Rol.Cliente);
            // guardamos en BD y obtenemos el Id asignado para hacer la relacion
            // y asi usuario y cliente tienen igual id
            Usuario usuarioConId = usuarioRepository.save(usuario);
            Cliente cliente = new Cliente(registroClienteDTO.getTelefono());
            cliente.setId(usuarioConId.getId());
            clienteRepository.save(cliente);
            return cliente;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // es el usuario que guardamos


    public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRepository) {
        super(baseRepository);
    }
}
