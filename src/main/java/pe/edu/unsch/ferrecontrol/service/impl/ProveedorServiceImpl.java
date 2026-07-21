package pe.edu.unsch.ferrecontrol.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.unsch.ferrecontrol.model.Proveedor;
import pe.edu.unsch.ferrecontrol.repository.ProveedorRepository;
import pe.edu.unsch.ferrecontrol.service.ProveedorService;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    @Override
    public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Override
    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);
    }

    @Override
    public long contarTodos() {
        return proveedorRepository.count();
    }
}
