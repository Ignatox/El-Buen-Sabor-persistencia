package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.EmpleadoDTO;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl extends BaseServiceImpl<Empleado, Long> implements EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public EmpleadoServiceImpl(BaseRepository<Empleado, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public EmpleadoDTO datosEmpleado(Long id) throws Exception{
        try{
            Empleado empleado = empleadoRepository.buscarPorId(id);
            EmpleadoDTO empleadoDTO= new EmpleadoDTO();

            empleadoDTO.setIdEmpleado(empleado.getId());
            empleadoDTO.setNombre(empleado.getNombre());
            empleadoDTO.setApellido(empleado.getApellido());
            empleadoDTO.setEmail(empleado.getEmail());
            empleadoDTO.setTelefono(empleado.getTelefono());
            empleadoDTO.setDomicilio(empleado.getDomicilios());
            empleadoDTO.setContrasena(empleado.getUsuario().getPassword());

            return empleadoDTO;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }



    }

    @Override
    @Transactional
    public Empleado modificardatos(EmpleadoDTO empleadoDTO) throws Exception{
        try{
            Empleado empleado = empleadoRepository.buscarPorId(empleadoDTO.getIdEmpleado());

            if(empleadoDTO.getEmail() != null && !empleadoDTO.getEmail().isEmpty())
                empleado.setEmail(empleadoDTO.getEmail());

            if(empleadoDTO.getTelefono() != null && !empleadoDTO.getTelefono().isEmpty())
                empleado.setTelefono(empleadoDTO.getTelefono());

            List<Domicilio> domiciliosEmpleados = empleado.getDomicilios();
            List<Domicilio> domiciliosDTO = empleadoDTO.getDomicilio();
            for(Domicilio domicilio : domiciliosDTO){
                if(!domiciliosEmpleados.contains(domicilio)){
                    domiciliosEmpleados.add(domicilio);
                }
            }


            Usuario usuarioEmpleado = usuarioRepository.buscarPorId(empleadoDTO.getIdEmpleado());

            //DATOS PARA LA VERIFICACION DE CONTRASEÑA
            final int MAX=8;
            final int MIN_Uppercase = 1;
            final int MIN_Lowercase = 1;
            final int NUM_Digits = 1;
            final int Special = 1;
            int uppercaseCounter = 0;
            int lowercaseCounter = 0;
            int digitCounter = 0;
            int specialCounter = 0;

            for (int i = 0; i < usuarioEmpleado.getPassword().length(); i++) {
                char c = usuarioEmpleado.getPassword().charAt(i);
                if (Character.isUpperCase(c))
                    uppercaseCounter++;
                else if (Character.isLowerCase(c))
                    lowercaseCounter++;
                else if (Character.isDigit(c))
                    digitCounter++;
                //revisar
                if (c >= 33 && c <= 46 || c == 64) {
                    specialCounter++;
                }
            }

            if (empleadoDTO.getContrasena().length() >= MAX && uppercaseCounter >= MIN_Uppercase && lowercaseCounter >= MIN_Lowercase && digitCounter >= NUM_Digits && specialCounter >= Special) {
                usuarioEmpleado.setPassword(empleadoDTO.getContrasena());

            } else {
                throw new Exception("la contraseña no tiene los requisitos adecuados");
            }

            empleadoRepository.save(empleado);
            return empleado;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    //Metodo para registrarEmpleado y verificar anteriormente que no exista un empleado con ese mail

    @Override
    @Transactional
    public Empleado registrarEmpleado(EmpleadoDTO empleadoDTO) throws Exception {
        try{
            //Verifico si ya existe un empleado con el mail ingresado
            Empleado empleadoExistente = empleadoRepository.buscarPorEmail(empleadoDTO.getEmail());
            if (empleadoExistente != null){
                throw new EmpleadoExistenteException("Ya existe un empleado con el mismo mail");
            }

            if (!validarContraseña(empleadoDTO.getContrasena())) {
                throw new ContraseñaInvalidaException("La contraseña no cumple con los requisitos mínimos.");
            }
            Empleado nuevoEmpleado = new Empleado();
            nuevoEmpleado.setNombre(empleadoDTO.getNombre());
            nuevoEmpleado.setEmail(empleadoDTO.getEmail());
            nuevoEmpleado.setApellido(empleadoDTO.getApellido());
            nuevoEmpleado.setTelefono(empleadoDTO.getTelefono());
            nuevoEmpleado.setDomicilios(empleadoDTO.getDomicilio());

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nuevoEmpleado.getNombre());
            nuevoUsuario.setPassword(empleadoDTO.getContrasena());
            nuevoUsuario.setRol(empleadoDTO.getRol());

            nuevoEmpleado.setUsuario(nuevoUsuario);
            empleadoRepository.save(nuevoEmpleado);
            return nuevoEmpleado;
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
