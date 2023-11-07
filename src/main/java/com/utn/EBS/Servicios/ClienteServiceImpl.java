package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ClienteDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Entidades.Domicilio;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.ClienteRepository;
import com.utn.EBS.Repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ClienteServiceImpl extends BaseServiceImpl<Cliente, Long>
implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ClienteServiceImpl(BaseRepository<Cliente, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public ClienteDTO datosCliente(Long id) throws Exception{
        try{
            Cliente cliente= clienteRepository.buscarPorId(id);
            ClienteDTO clienteDTO= new ClienteDTO();

            clienteDTO.setIdCliente(cliente.getId());
            clienteDTO.setNombre(cliente.getNombre());
            clienteDTO.setApellido(cliente.getApellido());
            clienteDTO.setEmail(cliente.getEmail());
            clienteDTO.setTelefono(cliente.getTelefono());
            clienteDTO.setDomicilio(cliente.getDomicilios());
            clienteDTO.setContrasena(cliente.getUsuario().getPassword());

            return clienteDTO;
         }catch (Exception e){
            throw new Exception(e.getMessage());
        }



    }

    @Override
    @Transactional
    public Cliente modificardatos(ClienteDTO clienteDto) throws Exception{
        try{
            Cliente cliente= clienteRepository.buscarPorId(clienteDto.getIdCliente());

            if(clienteDto.getEmail() != null && !clienteDto.getEmail().isEmpty())
            cliente.setEmail(clienteDto.getEmail());

            if(clienteDto.getTelefono() != null && !clienteDto.getTelefono().isEmpty())
                cliente.setTelefono(clienteDto.getTelefono());

            List<Domicilio> domiciliosClientes = cliente.getDomicilios();
            List<Domicilio> domiciliosDTO = clienteDto.getDomicilio();
            for(Domicilio domicilio : domiciliosDTO){
                if(!domiciliosClientes.contains(domicilio)){
                    domiciliosClientes.add(domicilio);
                }
            }

            Usuario usuarioCliente = usuarioRepository.buscarPorId(clienteDto.getIdCliente());

            //DATOS PARA LA VERIFICACION DE CONTRASEÑA
            final int MAX=8;
            final int MIN_Uppercase = 1;
            final int MIN_Lowercase = 1;
            final int NUM_Digits = 1;
            final int Special = 1;
            int uppercaseCounter = 0;
            int lowercaseCounter = 0;
            int digitCounter = 0;
            int specialCounter = 0;

            for (int i = 0; i < usuarioCliente.getPassword().length(); i++) {
                char c = usuarioCliente.getPassword().charAt(i);
                if (Character.isUpperCase(c))
                    uppercaseCounter++;
                else if (Character.isLowerCase(c))
                    lowercaseCounter++;
                else if (Character.isDigit(c))
                    digitCounter++;
                //revisar
                if (c >= 33 && c <= 46 || c == 64) {
                    specialCounter++;
                }
            }

            if (clienteDto.getContrasena().length() >= MAX && uppercaseCounter >= MIN_Uppercase && lowercaseCounter >= MIN_Lowercase && digitCounter >= NUM_Digits && specialCounter >= Special) {
                usuarioCliente.setPassword(clienteDto.getContrasena());

            } else {
                throw new Exception("la contraseña no tiene los requisitos adecuados");
            }

            clienteRepository.save(cliente);
            return cliente;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }
}
