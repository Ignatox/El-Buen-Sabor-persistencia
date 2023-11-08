package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ModificarEmpleadoDTO;
import com.utn.EBS.DTO.RegistrarEmpleadoDTO;
import com.utn.EBS.Entidades.Empleado;

public interface EmpleadoService extends BaseService<Empleado, Long>{
    public RegistrarEmpleadoDTO datosEmpleado(Long id) throws Exception;

    public Empleado modificardatos(ModificarEmpleadoDTO modificarEmpleadoDTO) throws Exception;

    // public Empleado actualizarContrasena(EmpleadoDTO empleadoDto) throws Exception;
    public Empleado registrarEmpleado(RegistrarEmpleadoDTO registrarEmpleadoDTO) throws Exception;
}
