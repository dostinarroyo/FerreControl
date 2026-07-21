package pe.edu.unsch.ferrecontrol.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pe.edu.unsch.ferrecontrol.model.Categoria;
import pe.edu.unsch.ferrecontrol.model.DetalleVenta;
import pe.edu.unsch.ferrecontrol.model.Producto;
import pe.edu.unsch.ferrecontrol.model.Proveedor;
import pe.edu.unsch.ferrecontrol.model.Venta;
import pe.edu.unsch.ferrecontrol.service.CategoriaService;
import pe.edu.unsch.ferrecontrol.service.ProductoService;
import pe.edu.unsch.ferrecontrol.service.ProveedorService;
import pe.edu.unsch.ferrecontrol.service.VentaService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataLoader {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final ProveedorService proveedorService;
    private final VentaService ventaService;

    public DataLoader(CategoriaService categoriaService,
                      ProductoService productoService,
                      ProveedorService proveedorService,
                      VentaService ventaService) {
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.proveedorService = proveedorService;
        this.ventaService = ventaService;
    }

    @PostConstruct
    public void initData() {
        if (categoriaService.contarTodos() > 0 || productoService.contarTodos() > 0 || proveedorService.contarTodos() > 0 || ventaService.contarTodos() > 0) {
            return;
        }

        Categoria electricidad = new Categoria();
        electricidad.setNombre("Electricidad");
        electricidad.setDescripcion("Materiales eléctricos y herramientas para instalaciones.");
        electricidad = categoriaService.guardar(electricidad);

        Categoria ferreteria = new Categoria();
        ferreteria.setNombre("Ferretería");
        ferreteria.setDescripcion("Elementos básicos de ferretería para obra y hogar.");
        ferreteria = categoriaService.guardar(ferreteria);

        Categoria construccion = new Categoria();
        construccion.setNombre("Construcción");
        construccion.setDescripcion("Materiales para construcción, albañilería y acabados.");
        construccion = categoriaService.guardar(construccion);

        Proveedor amaPro = new Proveedor();
        amaPro.setNombre("AmaPro S.A.");
        amaPro.setTelefono("(074) 500-123");
        amaPro.setDireccion("Av. 9 de Diciembre 145, Ayacucho");
        proveedorService.guardar(amaPro);

        Proveedor ferreAndina = new Proveedor();
        ferreAndina.setNombre("FerreAndina");
        ferreAndina.setTelefono("(074) 501-789");
        ferreAndina.setDireccion("Jr. Independencia 257, Ayacucho");
        proveedorService.guardar(ferreAndina);

        Proveedor construcciónAyacucho = new Proveedor();
        construcciónAyacucho.setNombre("Construcción Ayacucho");
        construcciónAyacucho.setTelefono("(074) 502-456");
        construcciónAyacucho.setDireccion("Psj. La Mar 10, Ayacucho");
        proveedorService.guardar(construcciónAyacucho);

        Producto producto1 = new Producto();
        producto1.setNombre("Cemento Portland");
        producto1.setDescripcion("Bolsa de 42.5 kg para construcción general.");
        producto1.setPrecio(new BigDecimal("35.50"));
        producto1.setStock(120);
        producto1.setCategoria(construccion);
        producto1.setImagenUrl("https://via.placeholder.com/400x240?text=Cemento");
        productoService.guardar(producto1);

        Producto producto2 = new Producto();
        producto2.setNombre("Cable eléctrico 2.5 mm");
        producto2.setDescripcion("Rollo de 100 metros para instalación domiciliaria.");
        producto2.setPrecio(new BigDecimal("180.00"));
        producto2.setStock(65);
        producto2.setCategoria(electricidad);
        producto2.setImagenUrl("https://via.placeholder.com/400x240?text=Cable");
        productoService.guardar(producto2);

        Producto producto3 = new Producto();
        producto3.setNombre("Martillo carpintero");
        producto3.setDescripcion("Martillo de acero con mango antideslizante.");
        producto3.setPrecio(new BigDecimal("45.00"));
        producto3.setStock(40);
        producto3.setCategoria(ferreteria);
        producto3.setImagenUrl("https://via.placeholder.com/400x240?text=Martillo");
        productoService.guardar(producto3);

        Producto producto4 = new Producto();
        producto4.setNombre("Pintura látex 4L");
        producto4.setDescripcion("Pintura interior blanco mate de secado rápido.");
        producto4.setPrecio(new BigDecimal("110.00"));
        producto4.setStock(32);
        producto4.setCategoria(ferreteria);
        producto4.setImagenUrl("https://via.placeholder.com/400x240?text=Pintura");
        productoService.guardar(producto4);

        Venta venta1 = new Venta();
        venta1.setCliente("Taller Hermanos López");
        venta1.setFecha(LocalDateTime.now().minusDays(2));
        DetalleVenta detalle1 = new DetalleVenta();
        detalle1.setProducto(producto1);
        detalle1.setCantidad(10);
        detalle1.setPrecioUnitario(producto1.getPrecio());
        detalle1.setSubtotal(producto1.getPrecio().multiply(new BigDecimal(detalle1.getCantidad())));
        venta1.addDetalle(detalle1);
        venta1.setTotal(detalle1.getSubtotal());
        ventaService.guardar(venta1);

        Venta venta2 = new Venta();
        venta2.setCliente("Constructora San José");
        venta2.setFecha(LocalDateTime.now().minusDays(1));
        DetalleVenta detalle2 = new DetalleVenta();
        detalle2.setProducto(producto2);
        detalle2.setCantidad(5);
        detalle2.setPrecioUnitario(producto2.getPrecio());
        detalle2.setSubtotal(producto2.getPrecio().multiply(new BigDecimal(detalle2.getCantidad())));
        venta2.addDetalle(detalle2);
        DetalleVenta detalle3 = new DetalleVenta();
        detalle3.setProducto(producto3);
        detalle3.setCantidad(3);
        detalle3.setPrecioUnitario(producto3.getPrecio());
        detalle3.setSubtotal(producto3.getPrecio().multiply(new BigDecimal(detalle3.getCantidad())));
        venta2.addDetalle(detalle3);
        venta2.setTotal(detalle2.getSubtotal().add(detalle3.getSubtotal()));
        ventaService.guardar(venta2);
    }
}
