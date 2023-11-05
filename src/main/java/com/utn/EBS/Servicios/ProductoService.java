package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.Entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService extends BaseService<Producto, Long>{
List<Producto> buscarPorDenominacion( String denominacion)throws Exception;

    List<Producto> buscarPorTipoProducto(TipoProducto tipoProducto)throws Exception;
    Page<Producto> buscarPorDenominacion(String denominacion, Pageable pageable) throws Exception;

    Page<Producto> buscarPorTipoProducto(TipoProducto tipoProducto, Pageable pageable) throws Exception;

    // Estos metodos son de ingrediente, no de producto
    //List<Producto> ProductosAReponer() throws Exception;
    //Page<Producto> ProductosAReponer( Pageable pageable) throws Exception;

    public Producto agregarProducto(AgregarProductoDTO agregarProductoDTO) throws Exception;
}
