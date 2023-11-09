package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.*;
import com.utn.EBS.Entidades.*;
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
    public List<RubroPantallaPrincipalDTO> traerProductosPaginaPrincipal() throws Exception {
        try {
            List<Rubro> rubros = rubroRepository.findAll();
            List<RubroPantallaPrincipalDTO> listaRubrosProductos = new ArrayList<>();
            for (Rubro rubro : rubros) {
                List<ProductoPantallaPrincipalDTO> productosRubroEnviar = productoRepository.buscarPorRubroDTO(rubro.getId());
                RubroPantallaPrincipalDTO rubroEnviar = RubroPantallaPrincipalDTO.builder()
                        .nombreRubro(rubro.getNombre())
                        .productos(productosRubroEnviar)
                        .build();
                listaRubrosProductos.add(rubroEnviar);
            }
            return listaRubrosProductos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<RankingProductoDTO> traerRankingProductos(BuscarRankingProductosDTO buscarRankingProductosDTO) throws Exception {
        try {
            List<RankingProductoDTO> rankingProductoDTOS = productoRepository.buscarRankingProductos(buscarRankingProductosDTO.getFechaDesde(), buscarRankingProductosDTO.getFechaHasta());
            for (RankingProductoDTO dto: rankingProductoDTOS) {
                System.out.print(dto.getNombreProducto());
            }
            return rankingProductoDTOS;
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