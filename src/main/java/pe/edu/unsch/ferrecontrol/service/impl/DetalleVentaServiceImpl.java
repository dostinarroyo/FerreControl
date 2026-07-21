package pe.edu.unsch.ferrecontrol.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.unsch.ferrecontrol.model.DetalleVenta;
import pe.edu.unsch.ferrecontrol.repository.DetalleVentaRepository;
import pe.edu.unsch.ferrecontrol.service.DetalleVentaService;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImpl(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> listarTodos() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta obtenerPorId(Long id) {
        return detalleVentaRepository.findById(id).orElse(null);
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void eliminar(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}
