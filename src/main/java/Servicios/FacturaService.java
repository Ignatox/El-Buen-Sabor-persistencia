package Servicios;

import Entidades.Factura;
import Repositorios.FacturaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService implements BaseService<Factura> {


    private final FacturaRepository facturaRepository;
    @Autowired
    public FacturaService (FacturaRepository facturaRepository) {       //Inyección de Constructor (Es mejor que usar solo @Autowired, si no arranca cambiar)
        this.facturaRepository = facturaRepository;
    }

    @Override
    @Transactional
    public List<Factura> findAll() throws Exception {
        try {
            List<Factura> entities = facturaRepository.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Factura findById(Long id) throws Exception {
        try {
            Optional<Factura> entityOptional = facturaRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Factura save(Factura entity) throws Exception {
        try {
            entity = facturaRepository.save(entity);
            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Factura update(Long id, Factura entity) throws Exception {
        try {
            Optional<Factura> entityOptional = facturaRepository.findById(id);
            Factura factura = entityOptional.get();                             //No va a largar error pero puede que sea inestable
            factura = facturaRepository.save(factura);
            return factura;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            if(facturaRepository.existsById(id)) {
                facturaRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
