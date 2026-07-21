package pe.edu.unsch.ferrecontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unsch.ferrecontrol.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
