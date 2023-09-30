package Controladores;

import Entidades.BaseEntidad;
import Servicios.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class BaseControllerImpl<E extends BaseEntidad, S extends BaseServiceImpl<E, Long>> implements BaseController<E, Long> {
@Autowired
    protected S servicio;
}