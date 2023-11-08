package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ModificarClienteDTO;
import com.utn.EBS.Entidades.Cliente;

import java.util.List;

public interface ClienteService extends BaseService<Cliente, Long>{
    public List<Cliente> mostrarClientes() throws Exception;
    public Cliente modificarCliente(ModificarClienteDTO clienteDTO) throws Exception;
}
