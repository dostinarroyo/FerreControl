package pe.edu.unsch.ferrecontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.unsch.ferrecontrol.service.CategoriaService;
import pe.edu.unsch.ferrecontrol.service.ProductoService;
import pe.edu.unsch.ferrecontrol.service.ProveedorService;
import pe.edu.unsch.ferrecontrol.service.VentaService;

@Controller
@RequestMapping("/")
public class InicioController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final ProveedorService proveedorService;
    private final VentaService ventaService;

    public InicioController(ProductoService productoService,
                            CategoriaService categoriaService,
                            ProveedorService proveedorService,
                            VentaService ventaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.proveedorService = proveedorService;
        this.ventaService = ventaService;
    }

    @GetMapping
    public String inicio(Model model) {
        model.addAttribute("titulo", "Ferrecontrol - Inicio");
        model.addAttribute("productosCount", productoService.contarTodos());
        model.addAttribute("categoriasCount", categoriaService.contarTodos());
        model.addAttribute("proveedoresCount", proveedorService.contarTodos());
        model.addAttribute("ventasCount", ventaService.contarTodos());
        model.addAttribute("totalVentas", ventaService.calcularTotalVentas());
        return "inicio";
    }

    @GetMapping("inicio")
    public String inicioAlias(Model model) {
        return inicio(model);
    }
}
