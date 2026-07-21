package pe.edu.unsch.ferrecontrol.service;

import pe.edu.unsch.ferrecontrol.model.Producto;
import java.util.List;

public interface ProductoService {
    List<Producto> listarTodos();
    Producto obtenerPorId(Long id);
    Producto guardar(Producto producto);
    void eliminar(Long id);
    long contarTodos();
}
