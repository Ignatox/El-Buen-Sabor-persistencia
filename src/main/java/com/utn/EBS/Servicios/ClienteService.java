package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ClienteDTO;
import com.utn.EBS.Entidades.Cliente;

public interface ClienteService extends BaseService<Cliente, Long>{

    public ClienteDTO datosCliente(Long id) throws Exception;

    public Cliente modificardatos(ClienteDTO clienteDto) throws Exception;
}
