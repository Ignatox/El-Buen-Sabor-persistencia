package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.ClienteDTO;
import com.utn.EBS.DTO.EmpleadoDTO;
import com.utn.EBS.Entidades.Persona;
import com.utn.EBS.Excepciones.EmpleadoExistenteException;
import com.utn.EBS.Servicios.PersonaService;
import com.utn.EBS.Servicios.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/persona")

public class PersonaController extends BaseControllerImpl<Persona, PersonaServiceImpl>{

    @GetMapping("/verDatos")
    public ResponseEntity<?> verDatosCliente(@RequestBody Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.datosCliente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/modificarDatos")
    public ResponseEntity<?> modificarDatosCliente(ClienteDTO clienteDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.modificardatos(clienteDTO));
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
    private PersonaService personaService;
    @PostMapping("/registrarEmpleado")
    public ResponseEntity<?> registrarEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        try {
            Persona empleado = personaService.registrarEmpleado(empleadoDTO);
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
