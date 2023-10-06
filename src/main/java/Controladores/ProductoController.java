package Controladores;

import Entidades.Producto;
import Servicios.ProductoServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/productos")

public class ProductoController extends BaseControllerImpl<Producto, ProductoServiceImpl>{
}
