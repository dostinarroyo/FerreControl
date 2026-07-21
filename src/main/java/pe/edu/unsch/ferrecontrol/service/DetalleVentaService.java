package pe.edu.unsch.ferrecontrol.service;

import pe.edu.unsch.ferrecontrol.model.DetalleVenta;
import java.util.List;

public interface DetalleVentaService {
    List<DetalleVenta> listarTodos();
    DetalleVenta obtenerPorId(Long id);
    DetalleVenta guardar(DetalleVenta detalleVenta);
    void eliminar(Long id);
}
