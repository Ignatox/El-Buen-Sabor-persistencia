package com.utn.EBS.Servicios;

import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl extends BaseServiceImpl<Producto, Long> implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public ProductoServiceImpl(BaseRepository<Producto, Long> baseRepository) {
        super(baseRepository);
    }

  @Override
    public List<Producto> ProductosAReponer() throws Exception {
        try {
            List<Producto> productosRep = productoRepository.reponerProducto();
            return productosRep;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Producto> ProductosAReponer(Pageable pageable) throws Exception {
        try {
            Page<Producto> productosRepPage = productoRepository.reponerProducto(pageable);
            return productosRepPage;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }    }

}