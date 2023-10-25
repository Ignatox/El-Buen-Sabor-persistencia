package com.utn.EBS.Servicios;

import com.utn.EBS.Entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService extends BaseService<Producto, Long>{

    List<Producto> ProductosAReponer() throws Exception;
    Page<Producto> ProductosAReponer( Pageable pageable) throws Exception;
}
