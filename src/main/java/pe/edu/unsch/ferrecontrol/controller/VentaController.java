package pe.edu.unsch.ferrecontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.unsch.ferrecontrol.model.Venta;
import pe.edu.unsch.ferrecontrol.service.VentaService;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaService.listarTodos());
        model.addAttribute("titulo", "Ventas - Ferrecontrol");
        return "ventas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("titulo", "Nueva Venta - Ferrecontrol");
        return "ventas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Venta venta) {
        ventaService.guardar(venta);
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Venta v = ventaService.obtenerPorId(id);
        model.addAttribute("venta", v != null ? v : new Venta());
        model.addAttribute("titulo", "Editar Venta - Ferrecontrol");
        return "ventas/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
        return "redirect:/ventas";
    }
}
