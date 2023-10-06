package Controladores;

import Entidades.Rubro;
import Servicios.RubroServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/rubros")
public class RubroController extends BaseControllerImpl<Rubro, RubroServiceImpl>{
}
