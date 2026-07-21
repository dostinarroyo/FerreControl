package pe.edu.unsch.ferrecontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unsch.ferrecontrol.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
