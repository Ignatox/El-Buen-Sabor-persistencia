package Servicios;

import Entidades.Producto;
import Repositorios.BaseRepository;
import Repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl extends BaseServiceImpl<Producto, Long>
        implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;

    public ProductoServiceImpl(BaseRepository<Producto, Long> baseRepository) {
        super(baseRepository);
    }
}