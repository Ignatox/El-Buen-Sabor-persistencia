package com.utn.EBS.Servicios;

import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RubroServiceImpl extends BaseServiceImpl<Rubro, Long> implements RubroService{
    @Autowired
    private RubroRepository rubroRepository;

    public RubroServiceImpl(BaseRepository<Rubro, Long> baseRepository) {
        super(baseRepository);
    }

    public List<Rubro> buscarPorNombre(String nombre) throws Exception {
        try{
            List<Rubro> rubrosPorNombre = rubroRepository.buscarPorNombre(nombre);
            return rubrosPorNombre;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
