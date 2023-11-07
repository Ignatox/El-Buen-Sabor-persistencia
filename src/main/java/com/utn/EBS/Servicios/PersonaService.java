package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ClienteDTO;
import com.utn.EBS.DTO.EmpleadoDTO;
import com.utn.EBS.Entidades.Persona;

public interface PersonaService extends BaseService<Persona, Long>{

    public ClienteDTO datosCliente(Long id) throws Exception;

    public Persona modificardatos(ClienteDTO clienteDto) throws Exception;

   // public Persona actualizarContrasena(EmpleadoDTO empleadoDto) throws Exception;
   public Persona registrarEmpleado(EmpleadoDTO empleadoDTO) throws Exception;
}
