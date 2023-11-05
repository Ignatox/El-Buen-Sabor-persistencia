package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.DTO.ProductoIngredienteDTO;
import com.utn.EBS.DTO.ProductoPantallaPrincipalDTO;
import com.utn.EBS.Entidades.Ingrediente;
import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.ProductoIngrediente;
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

@Service
public class ProductoServiceImpl extends BaseServiceImpl<Producto, Long> implements ProductoService {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    RubroRepository rubroRepository;
    @Autowired
    IngredienteRepository ingredienteRepository;
    @Autowired
    ProductoIngredienteRepository productoIngredienteRepository;

    public ProductoServiceImpl(BaseRepository<Producto, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public Producto agregarProducto(AgregarProductoDTO agregarProductoDTO) throws Exception {
        try {
            Rubro rubro = rubroRepository.findById(agregarProductoDTO.getIdRubro())
                    .orElseThrow(() -> new EntityNotFoundException("Rubro no encontrado con ID: " + agregarProductoDTO.getIdRubro()));

            Producto producto = Producto.builder()
                    .foto(agregarProductoDTO.getFoto())
                    .nombre(agregarProductoDTO.getNombre())
                    .descripcion(agregarProductoDTO.getDescripcion())
                    .precio(agregarProductoDTO.getPrecio())
                    .receta(agregarProductoDTO.getReceta())
                    .estadoProducto(agregarProductoDTO.getEstado())
                    .tiempoEstimadoCocina(agregarProductoDTO.getTiempoEstimadoCocina())
                    .rubro(rubro)
                    .build();
            productoRepository.save(producto);
            List<ProductoIngredienteDTO> ingredientes = agregarProductoDTO.getIngredienteDTOS();
            for (ProductoIngredienteDTO productoIngredienteDTO : ingredientes) {
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

    @Override
    public List<ProductoPantallaPrincipalDTO> traerProductosPaginaPrincipal() throws Exception {
        try {
            List<Producto> productos = productoRepository.findAll();
            List<ProductoPantallaPrincipalDTO> listaProductos = new ArrayList<>();

            for (Producto producto : productos) {
                ProductoPantallaPrincipalDTO productoDTO = ProductoPantallaPrincipalDTO.builder()
                        .nombre(producto.getNombre())
                        .descripcion(producto.getDescripcion())
                        .foto(producto.getFoto())
                        .precio(producto.getPrecio())
                        .tiempoEstimadoCocina(producto.getTiempoEstimadoCocina())
                        .rubro(producto.getRubro().getNombre())
                        .build();
                listaProductos.add(productoDTO);
            }
            return listaProductos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) throws Exception {
        try {
            List<Producto> productos = productoRepository.buscarPorNombre(nombre);
            return productos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Page<Producto> buscarPorNombre(String nombre, Pageable pageable) throws Exception {
        try {
            Page<Producto> productos = productoRepository.buscarPorNombre(nombre, pageable);
            return productos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}