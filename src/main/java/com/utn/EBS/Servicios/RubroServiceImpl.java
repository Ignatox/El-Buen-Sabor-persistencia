package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AgregarRubroDTO;
import com.utn.EBS.DTO.AltaRubroDTO;
import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Entidades.*;
import com.utn.EBS.Enumeraciones.EstadoRubro;
import com.utn.EBS.Repositorios.RubroRepository;
import jakarta.transaction.Transactional;
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
//    @Override
  //  @Transactional
    //public AltaRubroDTO CrearNuevoRubro(Long id) throws Exception{
      //  try{
        //    Rubro rubro = rubroRepository.buscarPorId(id);
          //  AltaRubroDTO altaRubroDTO = new AltaRubroDTO();

            //altaRubroDTO.setNombre(rubro.getNombre());
            //altaRubroDTO.setEstado(rubro.getEstado());
            //altaRubroDTO.setIngredienteRel(altaRubroDTO.getIngredienteRel());

         //   return altaRubroDTO;
      //  }catch (Exception e){
        //    throw new Exception(e.getMessage());
       // }




   // @Transactional
//    public Rubro agregarRubroIng(AltaRubroDTO altaRubroDTO) throws Exception {
  //      try {

//            Rubro rubro = Rubro.builder()
  //                  .nombre(altaRubroDTO.getNombre())
    //                .estado(altaRubroDTO.getEstado())
      //              .ingredientes(altaRubroDTO.getIngredienteRel())
        //            .build();
            // Obtener los ingredientes desde el DTO
           // List<Ingrediente> ingredientes = altaRubroDTO.getNombreIngrediente();

            // Asignar los ingredientes al rubro
            //rubro.setIngredientes(ingredientes);

            // Guardar el rubro con sus respectivos ingredientes
 //           rubroRepository.save(rubro);
   //         return rubro;
     //   } catch (Exception e) {
      //      throw  new Exception(e.getMessage());
       // }
  //  }



    //@Transactional

   // public AltaRubroDTO agregarRubroIng(Long id) throws Exception {
     //   try {
       //     Ingrediente ingrediente = ingredienteRepository.buscarPorId(id);
         //   Rubro rubro = rubroRepository.buscarPorId(id);
           // AltaRubroDTO altaRubroDTO = new AltaRubroDTO();

            //altaRubroDTO.setNombre(rubro.getNombre());
            //altaRubroDTO.setEstado(EstadoRubro.ACTIVO);
            //altaRubroDTO.setNombreIngrediente(ingrediente.getNombre());

            //return altaRubroDTO;
       // } catch (Exception e) {
         //   throw new Exception(e.getMessage());
      //  }

    //}



    //@Transactional
    //public Rubro editarRubro(AltaRubroDTO altaRubroDTO) throws Exception {
       // try {

            //Rubro rubroModificado = rubroRepository.buscarPorId(altaRubroDTO.getIdRubro());
           // if (altaRubroDTO.getNombre() != null && !altaRubroDTO.getNombre().isEmpty())
                //altaRubroDTO.setNombre(altaRubroDTO.getNombre());

           // if (altaRubroDTO.getEstado() != null)
                //altaRubroDTO.setEstado(EstadoRubro.MODIFICADO);

            //if(altaRubroDTO.getNombreIngrediente() != null && !altaRubroDTO.getNombreIngrediente().isEmpty())
             //  altaRubroDTO.setNombreIngrediente(altaRubroDTO.getNombreIngrediente());



           // rubroRepository.save(rubroModificado);
          //  return rubroModificado;

        //} catch (Exception e) {
           // throw new Exception(e.getMessage());
      //  }


    }

