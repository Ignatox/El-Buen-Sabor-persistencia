package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Factura;
import com.utn.EBS.Entidades.Ingrediente;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends BaseRepository <Ingrediente, Long>{
}
