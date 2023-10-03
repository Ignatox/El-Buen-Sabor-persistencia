package Controladores;

import Entidades.Cliente;
import Servicios.ClienteServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/clientes")

public class ClienteController extends BaseControllerImpl<Cliente, ClienteServiceImpl>{
}
