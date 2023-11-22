package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ModificarEmpleadoDTO;
import com.utn.EBS.DTO.RegistrarEmpleadoDTO;
import com.utn.EBS.Entidades.Empleado;
import com.utn.EBS.Entidades.Domicilio;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Excepciones.ContraseñaInvalidaException;
import com.utn.EBS.Excepciones.EmpleadoExistenteException;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.EmpleadoRepository;
import com.utn.EBS.Repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl extends BaseServiceImpl<Empleado, Long> implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public EmpleadoServiceImpl(BaseRepository<Empleado, Long> baseRepository, PasswordEncoder passwordEncoder) {
        super(baseRepository);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public RegistrarEmpleadoDTO datosEmpleado(Long id) throws Exception{
        try{
            Empleado empleado = empleadoRepository.buscarPorId(id);
            RegistrarEmpleadoDTO registrarEmpleadoDTO = new RegistrarEmpleadoDTO();

            registrarEmpleadoDTO.setNombre(empleado.getNombre());
            registrarEmpleadoDTO.setApellido(empleado.getApellido());
            registrarEmpleadoDTO.setEmail(empleado.getEmail());
            registrarEmpleadoDTO.setTelefono(empleado.getTelefono());
            registrarEmpleadoDTO.setDomicilio(empleado.getDomicilios());
            registrarEmpleadoDTO.setPassword(empleado.getUsuario().getPassword());

            return registrarEmpleadoDTO;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    //Este seria el modificardatos de EMPLEADO por el administrador (no puede modificar ni ver su contrasena)
    public Empleado modificardatos(ModificarEmpleadoDTO modificarEmpleadoDTO) throws Exception{
        try{
            //Empleado empleadoexistente = empleadoRepository.buscarPorEmail(modificarEmpleadoDTO.getEmail());
            Empleado empleado = empleadoRepository.buscarPorId(modificarEmpleadoDTO.getId());

            Usuario usuario = empleado.getUsuario();
            Empleado empleadoexistente = empleadoRepository.buscarPorEmail(modificarEmpleadoDTO.getEmail());

            //Si el empleado que encontre, no es el mismo al empleado que me pasaron por DTO, indico que ya existe un empleado con ese mail
            if (!(empleadoexistente.getId() == modificarEmpleadoDTO.getId())){
                throw new EmpleadoExistenteException("Ya existe un empleado con el mismo mail");
            }

            if(modificarEmpleadoDTO.getEmail() != null && !modificarEmpleadoDTO.getEmail().isEmpty()){

                empleado.setEmail(modificarEmpleadoDTO.getEmail());
            }

            if(modificarEmpleadoDTO.getTelefono() != null && !modificarEmpleadoDTO.getTelefono().isEmpty()){
                empleado.setTelefono(modificarEmpleadoDTO.getTelefono());}
            if(modificarEmpleadoDTO.getNombre() != null && !modificarEmpleadoDTO.getNombre().isEmpty()){
                empleado.setNombre(modificarEmpleadoDTO.getNombre());}
            if(modificarEmpleadoDTO.getApellido() != null && !modificarEmpleadoDTO.getApellido().isEmpty()){
                empleado.setApellido(modificarEmpleadoDTO.getApellido());}
          //  if(modificarEmpleadoDTO.getUsername() !=null && !modificarEmpleadoDTO.getUsername().isEmpty()){
             //   usuario.setUsername(modificarEmpleadoDTO.getUsername());
           // }
          //  if(modificarEmpleadoDTO.getRol() != null ){
            //    usuario.setRole(modificarEmpleadoDTO.getRol());}


            empleadoRepository.save(empleado);
            return empleado;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    //Metodo para registrarEmpleado y verificar anteriormente que no exista un empleado con ese mail



    @Override
    @Transactional

    public Empleado registrarEmpleado(RegistrarEmpleadoDTO registrarEmpleadoDTO) throws Exception {
        try{
            //Verifico si ya existe un empleado con el mail ingresado
            Empleado empleadoExistente = empleadoRepository.buscarPorEmail(registrarEmpleadoDTO.getEmail());
            if (empleadoExistente != null){
                throw new EmpleadoExistenteException("Ya existe un empleado con el mismo mail");
            }

            if (!validarContraseña(registrarEmpleadoDTO.getPassword())) {
                throw new ContraseñaInvalidaException("La contraseña no cumple con los requisitos mínimos.");
            }

            Empleado empleado = Empleado.builder()                 //Relacion 1 a 1 con usuario, puede q no vaya
             .nombre(registrarEmpleadoDTO.getNombre())
               .apellido(registrarEmpleadoDTO.getApellido())
               .telefono(registrarEmpleadoDTO.getTelefono())
               .email(registrarEmpleadoDTO.getEmail())
               .domicilios(registrarEmpleadoDTO.getDomicilio())
                .build();


            Usuario nuevoUsuario = Usuario.builder()
                    .username(registrarEmpleadoDTO.getUsername())
                            .role(registrarEmpleadoDTO.getRol())
                                    .password(passwordEncoder.encode(registrarEmpleadoDTO.getPassword()))
                                            .build();

            empleado.setUsuario(nuevoUsuario);
            empleadoRepository.save(empleado);
            return empleado;
        } catch (Exception e){
            throw new RuntimeException("error al registrar el empleado" + e.getMessage());
        }
    }
    private boolean validarContraseña(String contraseña) {
        if (contraseña.length() < 8) {
            return false; // La contraseña no tiene al menos 8 caracteres
        }

        boolean contieneMayuscula = false;
        boolean contieneMinuscula = false;
        boolean contieneSimbolo = false;

        for (char caracter : contraseña.toCharArray()) {
            if (Character.isUpperCase(caracter)) {
                contieneMayuscula = true;
            } else if (Character.isLowerCase(caracter)) {
                contieneMinuscula = true;
            } else if ("!@#$%^&*".indexOf(caracter) >= 0) {
                contieneSimbolo = true;
            }
        }

        return contieneMayuscula && contieneMinuscula && contieneSimbolo;
    }



}
