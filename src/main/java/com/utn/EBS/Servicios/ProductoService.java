package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.Entidades.Producto;

public interface ProductoService extends BaseService<Producto, Long>{

    public Producto agregarProducto(AgregarProductoDTO agregarProductoDTO) throws Exception;
}
