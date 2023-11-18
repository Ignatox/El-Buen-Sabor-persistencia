package desarrollo.sprint5.apiresttest.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarEmpleadoRequest {
    String username;
    String provisionalPassword;
    int idRole;

    String nombre;
    String apellido;
    String telefono;
    String mail;

    //Domicilio
    String calle;
    int nroCalle;
    int pisoDpto;
    int nroDpto;
    Long idlocalidad;
}