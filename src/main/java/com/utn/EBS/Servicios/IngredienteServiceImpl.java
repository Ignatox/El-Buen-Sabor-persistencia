package com.utn.EBS.Servicios;

import com.utn.EBS.Entidades.Ingrediente;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Optional;

public class IngredienteServiceImpl extends BaseServiceImpl<Ingrediente, Long> implements IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    public IngredienteServiceImpl(BaseRepository<Ingrediente, Long> baseRepository) {
        super(baseRepository);
    }

   public Ingrediente registrarCompraIngrediente(Long id, double costoCompra) throws Exception {
        try {

            Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
            ingrediente.ifPresent(ingrediente1 -> {
                ingrediente1.setCosto((float) costoCompra);
                ingredienteRepository.save(ingrediente1);
            });
            return ingrediente.get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    public Ingrediente actualizarStockCompraIngrediente(Long id, int cantComprada) throws Exception {
        try {
            Optional<Ingrediente> ingrediente= ingredienteRepository.findById(id);
            if (ingrediente.isPresent()){
                Ingrediente ingredienteActualizado = ingrediente.get();
                ingredienteActualizado.setStockActual(ingredienteActualizado.getStockActual() + cantComprada);
                ingredienteRepository.save(ingredienteActualizado);
                return ingredienteActualizado;
            } else
                throw new Exception("Producto no encontrado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}