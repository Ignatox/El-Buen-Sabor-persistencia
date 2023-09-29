package Servicios;

import java.util.List;

public interface BaseService<E>{
    public List<E> findAll() throws Exception;
    public E findById(Long id) throws Exception;
    public E save(E entidad) throws Exception;
    public E update(Long id, E entidad) throws Exception;
    public boolean delete(Long id) throws Exception;
}
