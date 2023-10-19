package com.utn.EBS.Servicios;

import com.utn.EBS.Entidades.Factura;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaServiceImpl extends BaseServiceImpl<Factura, Long>
        implements FacturaService{
    @Autowired
    private FacturaRepository facturaRepository;

    public FacturaServiceImpl(BaseRepository<Factura, Long> baseRepository) {
        super(baseRepository);
    }
}
