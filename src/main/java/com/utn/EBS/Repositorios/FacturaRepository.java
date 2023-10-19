package com.utn.EBS.Repositorios;

import com.utn.EBS.Entidades.Factura;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends BaseRepository <Factura, Long> {
}
