package pe.edu.unsch.ferrecontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unsch.ferrecontrol.model.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}
