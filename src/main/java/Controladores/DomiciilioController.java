package Controladores;

import Entidades.Domicilio;
import Servicios.DomicilioServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/domilicios")
public class DomiciilioController extends BaseControllerImpl<Domicilio, DomicilioServiceImpl>{
}