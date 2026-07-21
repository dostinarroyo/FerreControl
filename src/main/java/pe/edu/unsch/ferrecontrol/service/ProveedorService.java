package pe.edu.unsch.ferrecontrol.service;

import pe.edu.unsch.ferrecontrol.model.Proveedor;
import java.util.List;

public interface ProveedorService {
    List<Proveedor> listarTodos();
    Proveedor obtenerPorId(Long id);
    Proveedor guardar(Proveedor proveedor);
    void eliminar(Long id);
    long contarTodos();
}
