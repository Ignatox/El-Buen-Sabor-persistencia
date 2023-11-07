package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ClienteDTO;
import com.utn.EBS.DTO.EmpleadoDTO;
import com.utn.EBS.Entidades.Persona;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Entidades.Domicilio;
import com.utn.EBS.Excepciones.ContraseñaInvalidaException;
import com.utn.EBS.Excepciones.EmpleadoExistenteException;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.PersonaRepository;
import com.utn.EBS.Repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona, Long>
implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public ClienteDTO datosCliente(Long id) throws Exception{
        try{
            Persona persona = personaRepository.buscarPorId(id);
            ClienteDTO clienteDTO= new ClienteDTO();

            clienteDTO.setIdCliente(persona.getId());
            clienteDTO.setNombre(persona.getNombre());
            clienteDTO.setApellido(persona.getApellido());
            clienteDTO.setEmail(persona.getEmail());
            clienteDTO.setTelefono(persona.getTelefono());
            clienteDTO.setDomicilio(persona.getDomicilios());
            clienteDTO.setContrasena(persona.getUsuario().getPassword());

            return clienteDTO;
         }catch (Exception e){
            throw new Exception(e.getMessage());
        }



    }

    @Override
    @Transactional
    public Persona modificardatos(ClienteDTO clienteDto) throws Exception{
        try{
            Persona persona = personaRepository.buscarPorId(clienteDto.getIdCliente());

            if(clienteDto.getEmail() != null && !clienteDto.getEmail().isEmpty())
            persona.setEmail(clienteDto.getEmail());

            if(clienteDto.getTelefono() != null && !clienteDto.getTelefono().isEmpty())
                persona.setTelefono(clienteDto.getTelefono());

            List<Domicilio> domiciliosClientes = persona.getDomicilios();
            List<Domicilio> domiciliosDTO = clienteDto.getDomicilio();
            for(Domicilio domicilio : domiciliosDTO){
                if(!domiciliosClientes.contains(domicilio)){
                    domiciliosClientes.add(domicilio);
                }
            }


            Usuario usuarioCliente = usuarioRepository.buscarPorId(clienteDto.getIdCliente());

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

            for (int i = 0; i < usuarioCliente.getPassword().length(); i++) {
                char c = usuarioCliente.getPassword().charAt(i);
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

            if (clienteDto.getContrasena().length() >= MAX && uppercaseCounter >= MIN_Uppercase && lowercaseCounter >= MIN_Lowercase && digitCounter >= NUM_Digits && specialCounter >= Special) {
                usuarioCliente.setPassword(clienteDto.getContrasena());

            } else {
                throw new Exception("la contraseña no tiene los requisitos adecuados");
            }

            personaRepository.save(persona);
            return persona;

        } catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    //Metodo para registrarEmpleado y verificar anteriormente que no exista un empleado con ese mail

    @Override
    @Transactional
    public Persona registrarEmpleado(EmpleadoDTO empleadoDTO) throws Exception {
        try{
            //Verifico si ya existe un empleado con el mail ingresado
            Persona empleadoExistente = personaRepository.buscarPorEmail(empleadoDTO.getEmail());
            if (empleadoExistente != null){
                throw new EmpleadoExistenteException("Ya existe un empleado con el mismo mail");
            }

            if (!validarContraseña(empleadoDTO.getContrasena())) {
                throw new ContraseñaInvalidaException("La contraseña no cumple con los requisitos mínimos.");
            }
            Persona nuevoEmpleado = new Persona();
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
            personaRepository.save(nuevoEmpleado);
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

