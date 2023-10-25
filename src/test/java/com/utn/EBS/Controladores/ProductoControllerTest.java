package com.utn.EBS.Controladores;

import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.Enumeraciones.TipoProducto;
import com.utn.EBS.Servicios.ProductoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductoController.class)

class ProductoControllerTest {
@MockBean
private ProductoServiceImpl productoService;
@Autowired
private MockMvc mockMvc;
    @Test
    void buscarPorDenominacion() throws  Exception{
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
        when(productoService.buscarPorDenominacion("Hamburguesa")).thenReturn(listaEnviada);
        mockMvc.perform(get("/api/v1/productos/buscarPorDenominacion")
                .param("descripcion", "Hamburguesa")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$[0].denominacion", is("Hamburguesa")))
                .andExpect((ResultMatcher) jsonPath("$[0].tipoProducto", is(TipoProducto.MANUFACTURADO)))
                .andExpect((ResultMatcher) jsonPath("$[0].tiempoEstimadoCocina", is(120)))
                .andExpect((ResultMatcher) jsonPath("$[0].precioVenta", is(1300)))
                .andExpect((ResultMatcher) jsonPath("$[0].precioCompra", is(0)))
                .andExpect((ResultMatcher) jsonPath("$[0].stockActual", is(10)))
                .andExpect((ResultMatcher) jsonPath("$[0].stockMinimo", is(2)))
                .andExpect((ResultMatcher) jsonPath("$[0].unidadmedida", is("500gr")))
                .andExpect((ResultMatcher) jsonPath("$[0].foto", is("ham")))
                .andExpect((ResultMatcher) jsonPath("$[0].receta", is("pan y queso")))
                .andExpect((ResultMatcher) jsonPath("$[0].rubro", is(rubro1)))
                .andExpect((ResultMatcher) jsonPath("$[0].rubro.denominacion", is("PlatoPrincipal")))
                .andExpect((ResultMatcher) jsonPath("$[0].rubro.productos", is(producto)))

        ;
    }

    @Test
    void buscarPorTipoProducto() {
    }

    @Test
    void testBuscarPorDenominacio() {
    }

    @Test
    void testBuscarPorTipoProducto() {
    }
}