package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.DTO.ProductoIngredienteDTO;
import com.utn.EBS.Entidades.Ingrediente;
import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.ProductoIngrediente;
import com.utn.EBS.Enumeraciones.TipoProducto;
import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.Repositorios.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl extends BaseServiceImpl<Producto, Long> implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    RubroRepository rubroRepository;
    @Autowired
    IngredienteRepository ingredienteRepository;
    @Autowired
    ProductoIngredienteRepository productoIngredienteRepository;

    @Override
    @Transactional
    public Producto agregarProducto(AgregarProductoDTO agregarProductoDTO) throws Exception {
        try {
            /* Bueno va a ser horrible este metodo pero q funcione al menos
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
                    .tiempoEstimadoCocina(agregarProductoDTO.getTiempoEstimadoCocina())
                    .rubro(rubro)
                    .build();

            // Buscamos los ingredientes y creamos las relaciones
            List<ProductoIngredienteDTO> ingredientes = agregarProductoDTO.getIngredienteDTOS();
            List<ProductoIngrediente> productoIngredientes = new ArrayList<>();

            for (ProductoIngredienteDTO productoIngredienteDTO: ingredientes) {
                Ingrediente ingredienteRequerido = ingredienteRepository.findById(productoIngredienteDTO.getIngredienteId())
                        .orElseThrow(() -> new EntityNotFoundException("No se encontro uno de los ingredientes."));
                ProductoIngrediente productoIngrediente = ProductoIngrediente.builder()
                        .ingrediente(ingredienteRequerido)
                        .producto(producto)
                        .cantidad(productoIngredienteDTO.getCantidad())
                        .build();
                productoIngredientes.add(productoIngrediente);
            }
            //productoIngredienteRepository.saveAll(productoIngredientes);
            producto.setIngredientes(productoIngredientes);

            return productoRepository.save(producto);
            */
            Rubro rubro = rubroRepository.findById(agregarProductoDTO.getIdRubro())
                    .orElseThrow(() -> new EntityNotFoundException("Rubro no encontrado con ID: " + agregarProductoDTO.getIdRubro()));

            Producto producto = Producto.builder()
                    .tipoProducto(agregarProductoDTO.getTipoProducto())
                    .foto("foto")
                    .denominacion(agregarProductoDTO.getDenominacion())
                    .precioCompra(agregarProductoDTO.getPrecioCompra())
                    .precioVenta(agregarProductoDTO.getPrecioVenta())
                    .receta(agregarProductoDTO.getReceta())
                    .tiempoEstimadoCocina(agregarProductoDTO.getTiempoEstimadoCocina())
                    .rubro(rubro)
                    .build();
            productoRepository.save(producto);
            List<ProductoIngredienteDTO> ingredientes = agregarProductoDTO.getIngredienteDTOS();
            for (ProductoIngredienteDTO productoIngredienteDTO: ingredientes) {
                Ingrediente ingredienteRequerido = ingredienteRepository.findById(productoIngredienteDTO.getIngredienteId())
                        .orElseThrow(() -> new EntityNotFoundException("No se encontro uno de los ingredientes."));
                ProductoIngrediente productoIngrediente = ProductoIngrediente.builder()
                        .ingrediente(ingredienteRequerido)
                        .producto(producto)
                        .cantidad(productoIngredienteDTO.getCantidad())
                        .build();
                productoIngredienteRepository.save(productoIngrediente);
            }
            //productoIngredienteRepository.saveAll(productoIngredientes);
            return producto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ProductoServiceImpl(BaseRepository<Producto, Long> baseRepository) {
        super(baseRepository);
    }

@Override
    public List<Producto> buscarPorDenominacion( String denominacion)throws Exception{
    try {
        List<Producto> productos = productoRepository.buscarPorDenominacion(denominacion);
        return productos;
    }catch (Exception e){
        throw new Exception(e.getMessage());
    }
}
    @Override
    public List<Producto> buscarPorTipoProducto(TipoProducto tipoProducto)throws Exception{
        try {
            List<Producto> productos = productoRepository.buscarPorTipoProducto(tipoProducto);
            return productos;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
            }
        }

    // Estos metodos son de ingrediente, no de producto
  //@Override
  //  public List<Producto> ProductosAReponer() throws Exception {
   //     try {
   //         List<Producto> productosRep = productoRepository.reponerProducto();
   //         return productosRep;
   //     } catch (Exception e) {
    //        throw new Exception(e.getMessage());
    //    }
   // }

    @Override
    public Page<Producto> buscarPorDenominacion(String denominacion, Pageable pageable)throws Exception{
        try {
            Page<Producto> productos = productoRepository.buscarPorDenominacion(denominacion, pageable);
            return productos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public Page<Producto> buscarPorTipoProducto(TipoProducto tipoProducto, Pageable pageable)throws Exception{
        try {
            Page<Producto> productos = productoRepository.buscarPorTipoProducto(tipoProducto, pageable);
            return productos;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}