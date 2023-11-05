package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Entidades.ProductoIngrediente;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoIngredienteRepository extends BaseRepository <ProductoIngrediente, Long>{

    @Query("SELECT p FROM ProductoIngrediente p WHERE p.id = :id")
    ProductoIngrediente buscarPorId(@Param("id") Long id);
}
