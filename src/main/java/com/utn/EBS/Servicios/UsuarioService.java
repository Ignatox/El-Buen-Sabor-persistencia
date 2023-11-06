package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ModificarClienteDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.Usuario;

import java.util.List;

public interface UsuarioService extends BaseService<Usuario, Long>{
    public List<Cliente> mostrarClientes() throws Exception;
    public Cliente modificarCliente(ModificarClienteDTO clienteDTO) throws Exception;

}
