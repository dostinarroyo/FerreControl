package pe.edu.unsch.ferrecontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unsch.ferrecontrol.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
