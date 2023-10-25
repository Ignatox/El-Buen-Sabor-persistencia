package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.Enumeraciones.TipoProducto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ProductoRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void buscarPorDenominacion() {
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
        entityManager.persist(rubro1);
        entityManager.persist(producto);
        entityManager.flush();
        assertEquals(listaEnviada, productoRepository.buscarPorDenominacion("Hamburguesa"));
    }

    @Test
    void buscarPorTipoProducto() {
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
        entityManager.persist(rubro1);
        entityManager.persist(producto);
        entityManager.flush();
        assertEquals(listaEnviada, productoRepository.buscarPorTipoProducto(TipoProducto.MANUFACTURADO));
    }

    //@Test
    //void testBuscarPorDenominacion() {
    //}

    //@Test
    //void testBuscarPorTipoProducto() {
    //}
}