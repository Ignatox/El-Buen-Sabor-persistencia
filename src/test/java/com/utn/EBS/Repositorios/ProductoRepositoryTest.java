package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Producto;
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

        List<Producto> listaEnviada = new ArrayList<>();
        listaEnviada.add(producto);
        entityManager.persist(producto);
        entityManager.flush();
        assertEquals(listaEnviada, productoRepository.buscarPorDenominacion("Hamburguesa"));
    }

    @Test
    void buscarPorTipoProducto() {
        Producto producto= new Producto();
        producto.setDenominacion("Hamburguesa");
        producto.setTipoProducto(TipoProducto.MANUFACTURADO);

        List<Producto> listaEnviada = new ArrayList<>();
        listaEnviada.add(producto);
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