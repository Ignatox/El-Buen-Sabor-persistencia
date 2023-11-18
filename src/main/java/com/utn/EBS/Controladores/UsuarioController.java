package com.utn.EBS.Controladores;


import com.utn.EBS.DTO.CrearUsuarioDTO;
import com.utn.EBS.DTO.RegistrarEmpleadoDTO;
import com.utn.EBS.Entidades.Empleado;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Excepciones.EmpleadoExistenteException;
import com.utn.EBS.Repositorios.UsuarioRepository;
import com.utn.EBS.Servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/usuarios")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl>{

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @PostMapping("/crearUsuario") //Puede que haya q eliminar este metodo
    public ResponseEntity<?> crearUsuario(@RequestBody CrearUsuarioDTO crearUsuarioDTO) {
        try {
            Usuario usuario = usuarioService.crearUsuario(crearUsuarioDTO);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el empleado: " + e.getMessage());
        }
    }
}
