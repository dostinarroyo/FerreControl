package pe.edu.unsch.ferrecontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pe.edu.unsch.ferrecontrol.model.Categoria;
import pe.edu.unsch.ferrecontrol.model.Producto;
import pe.edu.unsch.ferrecontrol.service.CategoriaService;
import pe.edu.unsch.ferrecontrol.service.ProductoService;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Categoria.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isBlank()) {
                    setValue(null);
                    return;
                }
                try {
                    Long id = Long.valueOf(text);
                    Categoria categoria = categoriaService.obtenerPorId(id);
                    setValue(categoria);
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("titulo", "Productos - Ferrecontrol");
        return "productos/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listarTodos());
        model.addAttribute("titulo", "Nuevo Producto - Ferrecontrol");
        return "productos/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto p = productoService.obtenerPorId(id);
        model.addAttribute("producto", p != null ? p : new Producto());
        model.addAttribute("categorias", categoriaService.listarTodos());
        model.addAttribute("titulo", "Editar Producto - Ferrecontrol");
        return "productos/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}
