package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.DTO.BuscarRankingProductosDTO;
import com.utn.EBS.DTO.RubroPantallaPrincipalDTO;
import com.utn.EBS.DTO.RankingProductoDTO;
import com.utn.EBS.Entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService extends BaseService<Producto, Long>{
    List<Producto> buscarPorNombre( String nombre)throws Exception;

    Page<Producto> buscarPorNombre(String nombre, Pageable pageable) throws Exception;
    public Producto agregarProducto(AgregarProductoDTO agregarProductoDTO) throws Exception;
    public List<RubroPantallaPrincipalDTO> traerProductosPaginaPrincipal() throws Exception;
    public List<RankingProductoDTO> traerRankingProductos(BuscarRankingProductosDTO buscarRankingProductosDTO) throws Exception;
}
