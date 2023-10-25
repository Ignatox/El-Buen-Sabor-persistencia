package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.ProductoRepository;
import com.utn.EBS.Repositorios.RubroRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImpl extends BaseServiceImpl<Producto, Long>
        implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    RubroRepository rubroRepository;

    @Override
    @Transactional
    public Producto agregarProducto(AgregarProductoDTO agregarProductoDTO) throws Exception {
        try {
            // Buscamos el rubro
            Rubro rubro = rubroRepository.findById(agregarProductoDTO.getIdRubro())
                    .orElseThrow(() -> new EntityNotFoundException("Rubro no encontrado con ID: " + agregarProductoDTO.getIdRubro()));

            Producto producto = Producto.builder()
                    .tipoProducto(agregarProductoDTO.getTipoProducto())
                    .foto("foto")
                    .denominacion(agregarProductoDTO.getDenominacion())
                    .precioCompra(agregarProductoDTO.getPrecioCompra())
                    .precioVenta(agregarProductoDTO.getPrecioVenta())
                    .receta(agregarProductoDTO.getReceta())
                    .stockMinimo(agregarProductoDTO.getStockMinimo())
                    .stockAtual(agregarProductoDTO.getStockActual())
                    .unidadMedida(agregarProductoDTO.getUnidadMedida())
                    .tiempoEstimadoCocina(agregarProductoDTO.getTiempoEstimadoCocina())
                    .rubro(rubro)
                    .build();
            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ProductoServiceImpl(BaseRepository<Producto, Long> baseRepository) {
        super(baseRepository);
    }
}