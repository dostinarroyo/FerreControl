package pe.edu.unsch.ferrecontrol.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.unsch.ferrecontrol.model.Venta;
import pe.edu.unsch.ferrecontrol.repository.VentaRepository;
import pe.edu.unsch.ferrecontrol.service.VentaService;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> listarTodos() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta obtenerPorId(Long id) {
        return ventaRepository.findById(id).orElse(null);
    }

    @Override
    public Venta guardar(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }

    @Override
    public long contarTodos() {
        return ventaRepository.count();
    }

    @Override
    public java.math.BigDecimal calcularTotalVentas() {
        return ventaRepository.findAll().stream()
                .map(Venta::getTotal)
                .filter(java.util.Objects::nonNull)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
    }
}
