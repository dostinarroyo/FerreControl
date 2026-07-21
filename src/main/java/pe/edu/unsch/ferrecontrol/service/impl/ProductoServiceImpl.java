package pe.edu.unsch.ferrecontrol.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.unsch.ferrecontrol.model.Producto;
import pe.edu.unsch.ferrecontrol.repository.ProductoRepository;
import pe.edu.unsch.ferrecontrol.service.ProductoService;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public long contarTodos() {
        return productoRepository.count();
    }
}
