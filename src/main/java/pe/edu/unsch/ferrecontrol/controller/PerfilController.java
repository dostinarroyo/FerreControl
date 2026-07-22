package pe.edu.unsch.ferrecontrol.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.unsch.ferrecontrol.model.Usuario;
import pe.edu.unsch.ferrecontrol.service.UsuarioService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/mi-perfil")
public class PerfilController {

    private final UsuarioService usuarioService;

    public PerfilController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private Usuario obtenerUsuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String correo = auth.getName();
        return usuarioService.listarTodos().stream()
            .filter(x -> x.getCorreo().equalsIgnoreCase(correo))
            .findFirst().orElse(null);
    }

    @GetMapping
    public String verPerfil(Model model) {
        Usuario u = obtenerUsuarioAutenticado();
        model.addAttribute("usuario", u);
        model.addAttribute("titulo", "Mi Perfil - Ferrecontrol");
        return "perfil/ver";
    }

    @GetMapping("/editar")
    public String editarPerfil(Model model) {
        Usuario u = obtenerUsuarioAutenticado();
        model.addAttribute("usuario", u);
        model.addAttribute("titulo", "Editar Perfil - Ferrecontrol");
        return "perfil/editar";
    }

    @PostMapping("/guardar")
    public String guardarPerfil(Usuario usuarioForm) {
        Usuario u = obtenerUsuarioAutenticado();
        if (u == null) return "redirect:/login";
        u.setNombre(usuarioForm.getNombre());
        u.setCorreo(usuarioForm.getCorreo());
        usuarioService.guardar(u);
        return "redirect:/mi-perfil";
    }

    @PostMapping("/foto")
    public String subirFoto(@RequestParam("foto") MultipartFile foto) throws IOException {
        Usuario u = obtenerUsuarioAutenticado();
        if (u == null) return "redirect:/login";
        if (foto != null && !foto.isEmpty()) {
            Path uploadsPath = Paths.get("uploads/perfiles");
            if (!Files.exists(uploadsPath)) Files.createDirectories(uploadsPath);
            String ext = "";
            String original = foto.getOriginalFilename();
            int i = original.lastIndexOf('.');
            if (i > 0) ext = original.substring(i);
            String filename = UUID.randomUUID().toString() + ext;
            Path filePath = uploadsPath.resolve(filename);
            Files.copy(foto.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            u.setFoto(filename);
            usuarioService.guardar(u);
        }
        return "redirect:/mi-perfil";
    }

    @PostMapping("/password")
    public String cambiarPassword(@RequestParam("password") String password) {
        Usuario u = obtenerUsuarioAutenticado();
        if (u == null) return "redirect:/login";
        u.setPassword(password);
        usuarioService.guardar(u);
        return "redirect:/mi-perfil";
    }
}
