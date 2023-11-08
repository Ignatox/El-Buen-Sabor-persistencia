package com.utn.EBS.Controladores;


import com.utn.EBS.DTO.CambiarContraseñaDTO;
import com.utn.EBS.DTO.EmpleadoDTO;
import com.utn.EBS.Entidades.Empleado;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Excepciones.EmpleadoExistenteException;
import com.utn.EBS.Repositorios.EmpleadoRepository;
import com.utn.EBS.Servicios.EmpleadoService;
import com.utn.EBS.Servicios.EmpleadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class EmpleadoController extends BaseControllerImpl<Empleado, EmpleadoServiceImpl>{

    @GetMapping("/verDatosEmpleado")
    public ResponseEntity<?> verDatosEmpleado(@RequestBody Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.datosEmpleado(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/modificarDatosEmpleado")
    public ResponseEntity<?> modificarDatosEmpleado(EmpleadoDTO empleadoDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.modificardatos(empleadoDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    //  @GetMapping("/actualizarContraseña")
    //public ResponseEntity<?> actualizarContrasena(EmpleadoDTO empleadoDTO){
    //  try {
    //    return ResponseEntity.status(HttpStatus.OK).body(servicio.actualizarContrasena(empleadoDTO));
    //} catch (Exception e) {
    //  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    // }
    // }

    @Autowired
    private EmpleadoService empleadoService;
    @PostMapping("/registrarEmpleado")
    public ResponseEntity<?> registrarEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        try {
            Empleado empleado = empleadoService.registrarEmpleado(empleadoDTO);
            return ResponseEntity.ok(empleado);
        } catch (EmpleadoExistenteException e) {
            // Maneja el caso de un empleado duplicado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un empleado con el mismo correo electrónico: " + e.getMessage());
        } catch (Exception e) {
            // Maneja otros errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el empleado: " + e.getMessage());
        }
    }

}