package com.utn.EBS.Servicios;

import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.Enumeraciones.TipoProducto;
import com.utn.EBS.Repositorios.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductoServiceImplTest {
@MockBean
private ProductoRepository productoRepository;
@Autowired
private  ProductoServiceImpl productoService;
    @Test
    void buscarPorDenominacion() throws Exception{
        Producto producto= new Producto();
        producto.setDenominacion("Hamburguesa");
        producto.setTipoProducto(TipoProducto.MANUFACTURADO);
        producto.setTiempoEstimadoCocina(120);
        producto.setPrecioVenta(1300);
        producto.setPrecioCompra(0);
        producto.setStockAtual(10);
        producto.setStockMinimo(2);
        producto.setUnidadmedida("500gr");
        producto.setFoto("ham");
        producto.setReceta("pan y queso");
        Rubro rubro1 = new Rubro();
        rubro1.setDenominacion("PlatoPrincipal");
        producto.setRubro(rubro1);
        rubro1.agregarProducto(producto);

        List<Producto> listaEnviada = new ArrayList<>();
        listaEnviada.add(producto);
        when(productoRepository.buscarPorDenominacion("Hamburguesa")).thenReturn(listaEnviada);
        assertEquals(listaEnviada, productoService.buscarPorDenominacion("Hamburguesa"));
    }

    @Test
    void buscarPorTipoProducto()throws Exception {
        Producto producto= new Producto();
        producto.setDenominacion("Hamburguesa");
        producto.setTipoProducto(TipoProducto.MANUFACTURADO);
        producto.setTiempoEstimadoCocina(120);
        producto.setPrecioVenta(1300);
        producto.setPrecioCompra(0);
        producto.setStockAtual(10);
        producto.setStockMinimo(2);
        producto.setUnidadmedida("500gr");
        producto.setFoto("ham");
        producto.setReceta("pan y queso");
        Rubro rubro1 = new Rubro();
        rubro1.setDenominacion("PlatoPrincipal");
        producto.setRubro(rubro1);
        rubro1.agregarProducto(producto);

        List<Producto> listaEnviada = new ArrayList<>();
        listaEnviada.add(producto);
        when(productoRepository.buscarPorTipoProducto(TipoProducto.MANUFACTURADO)).thenReturn(listaEnviada);
        assertEquals(listaEnviada, productoService.buscarPorTipoProducto(TipoProducto.MANUFACTURADO));

    }

    @Test
    void testBuscarPorDenominacion() {
    }

    @Test
    void testBuscarPorTipoProducto() {
    }
}