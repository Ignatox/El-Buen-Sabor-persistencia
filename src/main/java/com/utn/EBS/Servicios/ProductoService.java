package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.DTO.ProductoPantallaPrincipalDTO;
import com.utn.EBS.Entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService extends BaseService<Producto, Long>{
    List<Producto> buscarPorNombre( String nombre)throws Exception;

    Page<Producto> buscarPorNombre(String nombre, Pageable pageable) throws Exception;

    public Producto agregarProducto(AgregarProductoDTO agregarProductoDTO) throws Exception;
    public List<ProductoPantallaPrincipalDTO> traerProductosPaginaPrincipal() throws Exception;
}
