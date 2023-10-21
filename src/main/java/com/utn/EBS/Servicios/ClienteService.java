package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.RegistroClienteDTO;
import com.utn.EBS.Entidades.Cliente;
import org.springframework.http.ResponseEntity;

public interface ClienteService extends BaseService<Cliente, Long>{

    Cliente registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception;
}
