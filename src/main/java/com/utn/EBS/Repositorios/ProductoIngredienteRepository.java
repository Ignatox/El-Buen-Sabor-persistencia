package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.ProductoIngrediente;
import com.utn.EBS.Enumeraciones.TipoProducto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoIngredienteRepository extends BaseRepository <ProductoIngrediente, Long>{

    @Query("SELECT p FROM ProductoIngrediente p WHERE p.id = :id")
    ProductoIngrediente buscarPorId(@Param("id") Long id);
}
