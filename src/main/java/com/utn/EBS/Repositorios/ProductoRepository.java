package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends BaseRepository <Producto, Long>{
}
