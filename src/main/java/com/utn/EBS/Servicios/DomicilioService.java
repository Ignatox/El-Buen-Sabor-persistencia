package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ClienteDomicilioDTO;
import com.utn.EBS.DTO.ModificarClienteDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.Domicilio;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DomicilioService extends BaseService<Domicilio, Long>{
    public List<Domicilio> mostrarDomiciliosCliente(Cliente cliente) throws Exception;
    public  Domicilio modificarDomicilioCliente(ClienteDomicilioDTO domicilioDTO) throws Exception;
}
