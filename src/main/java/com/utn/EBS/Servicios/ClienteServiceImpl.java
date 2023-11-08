package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ModificarClienteDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long>
implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRepository) {
        super(baseRepository);
    }
    @Override
    @Transactional

    public List<Cliente> mostrarClientes() throws  Exception{
        try {

            List<Cliente> clientes = clienteRepository.findAll();
            return clientes;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Cliente modificarCliente(ModificarClienteDTO clienteDTO) throws Exception{
        try {
            // buscamos al cliente

            Optional<Cliente> cliente = clienteRepository.findById(clienteDTO.getIdCliente());
            if (cliente.isEmpty()) throw new Exception("no se encontro el cliente");
            Cliente entityUpdate = new Cliente();
            entityUpdate.setApellido(clienteDTO.getApellido());
            entityUpdate.setEmail(clienteDTO.getEmail());
            entityUpdate.setNombre(clienteDTO.getNombre());
            entityUpdate.setTelefono(clienteDTO.getTelefono());
            entityUpdate.setFecha_modificacion(new Date());
            entityUpdate.setId(clienteDTO.getIdCliente());
            //List <ClienteDomicilioDTO> domidto;
            //domidto = clienteDTO.getDomicilios();
            //List <Domicilio> domi= null;
            //domi.add(domidto);
            //NO VOY A TOCAR DOMICILIO POR AHORA
            clienteRepository.save(entityUpdate);
            return entityUpdate;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
