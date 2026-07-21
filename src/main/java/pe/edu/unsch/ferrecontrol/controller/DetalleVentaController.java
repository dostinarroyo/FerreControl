package pe.edu.unsch.ferrecontrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pe.edu.unsch.ferrecontrol.model.DetalleVenta;
import pe.edu.unsch.ferrecontrol.model.Producto;
import pe.edu.unsch.ferrecontrol.model.Venta;
import pe.edu.unsch.ferrecontrol.service.DetalleVentaService;
import pe.edu.unsch.ferrecontrol.service.ProductoService;
import pe.edu.unsch.ferrecontrol.service.VentaService;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/detalle-ventas")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;
    private final VentaService ventaService;
    private final ProductoService productoService;

    public DetalleVentaController(DetalleVentaService detalleVentaService,
                                  VentaService ventaService,
                                  ProductoService productoService) {
        this.detalleVentaService = detalleVentaService;
        this.ventaService = ventaService;
        this.productoService = productoService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Venta.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isBlank()) {
                    setValue(null);
                    return;
                }
                try {
                    Long id = Long.valueOf(text);
                    setValue(ventaService.obtenerPorId(id));
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });
        binder.registerCustomEditor(Producto.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isBlank()) {
                    setValue(null);
                    return;
                }
                try {
                    Long id = Long.valueOf(text);
                    setValue(productoService.obtenerPorId(id));
                } catch (NumberFormatException e) {
                    setValue(null);
                }
            }
        });
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("detalleVentas", detalleVentaService.listarTodos());
        model.addAttribute("titulo", "Detalle de Ventas - Ferrecontrol");
        return "detalle-ventas/list";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("detalleVenta", new DetalleVenta());
        model.addAttribute("ventas", ventaService.listarTodos());
        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("titulo", "Nuevo Detalle de Venta - Ferrecontrol");
        return "detalle-ventas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DetalleVenta detalleVenta) {
        detalleVentaService.guardar(detalleVenta);
        return "redirect:/detalle-ventas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        DetalleVenta d = detalleVentaService.obtenerPorId(id);
        model.addAttribute("detalleVenta", d != null ? d : new DetalleVenta());
        model.addAttribute("ventas", ventaService.listarTodos());
        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("titulo", "Editar Detalle de Venta - Ferrecontrol");
        return "detalle-ventas/form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        detalleVentaService.eliminar(id);
        return "redirect:/detalle-ventas";
    }
}
