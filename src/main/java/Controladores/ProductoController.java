package Controladores;

import Entidades.Cliente;
import Entidades.Producto;
import Servicios.ClienteServiceImpl;
import Servicios.ProductServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/clientes")

public class ProductoController extends BaseControllerImpl<Producto, ProductServiceImpl>{
}
