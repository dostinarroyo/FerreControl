package pe.edu.unsch.ferrecontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.unsch.ferrecontrol.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreoIgnoreCase(String correo);
}
