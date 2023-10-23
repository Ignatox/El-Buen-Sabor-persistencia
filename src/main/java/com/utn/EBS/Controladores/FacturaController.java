package com.utn.EBS.Controladores;

import com.utn.EBS.Entidades.Factura;
import com.utn.EBS.Servicios.FacturaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/facturas")
public class FacturaController extends BaseControllerImpl<Factura, FacturaServiceImpl>{
}
