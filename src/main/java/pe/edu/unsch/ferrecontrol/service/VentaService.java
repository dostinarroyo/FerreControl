package pe.edu.unsch.ferrecontrol.service;

import pe.edu.unsch.ferrecontrol.model.Venta;
import java.util.List;

public interface VentaService {
    List<Venta> listarTodos();
    Venta obtenerPorId(Long id);
    Venta guardar(Venta venta);
    void eliminar(Long id);
    long contarTodos();
    java.math.BigDecimal calcularTotalVentas();
}
