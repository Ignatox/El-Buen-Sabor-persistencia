package Servicios;

import Entidades.Factura;
import Entidades.Producto;
import Repositorios.BaseRepository;
import Repositorios.FacturaRepository;
import Repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Producto, Long>
        implements ProductoService{
    @Autowired
    private ProductoRepository ProductoRepository;

    public ProductServiceImpl(BaseRepository<Producto, Long> baseRepository) {
        super(baseRepository);
    }
}