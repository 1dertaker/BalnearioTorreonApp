package repo;

import java.util.List;
import java.util.Optional;

public interface IRepositorio<T> {
    T agregar(T entidad);
    List<T> listar();
    Optional<T> buscarPorId(int id);
    boolean eliminar(int id);
}
