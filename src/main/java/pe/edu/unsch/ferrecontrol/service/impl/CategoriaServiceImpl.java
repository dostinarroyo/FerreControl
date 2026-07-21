package pe.edu.unsch.ferrecontrol.service.impl;

import org.springframework.stereotype.Service;
import pe.edu.unsch.ferrecontrol.model.Categoria;
import pe.edu.unsch.ferrecontrol.repository.CategoriaRepository;
import pe.edu.unsch.ferrecontrol.service.CategoriaService;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public long contarTodos() {
        return categoriaRepository.count();
    }
}
