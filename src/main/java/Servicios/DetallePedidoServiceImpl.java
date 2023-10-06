package Servicios;

import Entidades.DetallePedido;
import Repositorios.BaseRepository;
import Repositorios.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoServiceImpl extends BaseServiceImpl<DetallePedido, Long> implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    public DetallePedidoServiceImpl(BaseRepository<DetallePedido, Long> baseRepository) {
        super(baseRepository);
    }
}
