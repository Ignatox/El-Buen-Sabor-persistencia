package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ClienteDTO;
import com.utn.EBS.DTO.EmpleadoDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.Empleado;

public interface EmpleadoService extends BaseService<Empleado, Long>{
    public EmpleadoDTO datosEmpleado(Long id) throws Exception;

    public Empleado modificardatos(EmpleadoDTO empleadoDto) throws Exception;

    // public Empleado actualizarContrasena(EmpleadoDTO empleadoDto) throws Exception;
    public Empleado registrarEmpleado(EmpleadoDTO empleadoDTO) throws Exception;
}
