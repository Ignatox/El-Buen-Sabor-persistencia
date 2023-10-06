package Servicios;

import Controladores.BaseControllerImpl;
import Entidades.Rubro;
import Repositorios.BaseRepository;
import Repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RubroServiceImpl extends BaseServiceImpl<Rubro, Long> implements RubroService{
    @Autowired
    private RubroRepository rubroRepository;

    public RubroServiceImpl(BaseRepository<Rubro, Long> baseRepository) {
        super(baseRepository);
    }
}
