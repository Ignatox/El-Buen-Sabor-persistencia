package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AgregarRubroDTO;
import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.DTO.AltaRubroDTO;



public interface RubroService extends BaseService<Rubro, Long>{
    public Rubro agregarRubro(AgregarRubroDTO agregarRubroDTO) throws Exception;
    public Rubro editarRubro(Long id, AgregarRubroDTO agregarRubroDTO) throws  Exception;
}
