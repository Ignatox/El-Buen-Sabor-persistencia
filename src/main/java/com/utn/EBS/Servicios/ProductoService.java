package com.utn.EBS.Servicios;

import com.utn.EBS.Entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService extends BaseService<Producto, Long>{

    List<Producto> ProductosAReponer(int unidades) throws Exception;
    Page<Producto> ProductosAReponer(int unidades, Pageable pageable) throws Exception;
}
