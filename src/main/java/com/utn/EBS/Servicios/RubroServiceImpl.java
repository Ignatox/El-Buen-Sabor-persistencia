package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AgregarRubroDTO;
import com.utn.EBS.DTO.EditarRubroDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Rubro agregarRubro(AgregarRubroDTO agregarRubroDTO) throws Exception {
        try {
            Rubro rubro = Rubro.builder()
                    .nombre(agregarRubroDTO.getNombre())
                    .tipoRubro(agregarRubroDTO.getTipoRubro())
                    .estado(agregarRubroDTO.getEstado())
                    .build();
            //Faltaria ver como se agregan los productos al rubro
            rubroRepository.save(rubro);
            return rubro;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public Rubro editarRubro(Long id,AgregarRubroDTO agregarRubroDTO) throws Exception{
        try{
            Optional<Rubro> rubroAct = rubroRepository.findById(id);

                   Rubro rubro= rubroAct.get();
                   rubro.setNombre(agregarRubroDTO.getNombre());
                   rubro.setTipoRubro(agregarRubroDTO.getTipoRubro());
                   rubro.setEstado(agregarRubroDTO.getEstado());

           rubroRepository.save(rubro);
           return rubro;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }



    }

