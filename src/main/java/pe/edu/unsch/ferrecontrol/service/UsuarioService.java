package pe.edu.unsch.ferrecontrol.service;

import pe.edu.unsch.ferrecontrol.model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listarTodos();
    Usuario obtenerPorId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
}
