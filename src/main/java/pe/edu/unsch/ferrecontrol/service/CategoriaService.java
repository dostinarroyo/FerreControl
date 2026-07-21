package pe.edu.unsch.ferrecontrol.service;

import pe.edu.unsch.ferrecontrol.model.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> listarTodos();
    Categoria obtenerPorId(Long id);
    Categoria guardar(Categoria categoria);
    void eliminar(Long id);
    long contarTodos();
}
