package pe.edu.ecommerceZapatillas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.ecommerceZapatillas.dto.ProductosDto;
import pe.edu.ecommerceZapatillas.dto.RolesDto;
import pe.edu.ecommerceZapatillas.dto.UsuariosDto;
import pe.edu.ecommerceZapatillas.dto.VentasDto;
import pe.edu.ecommerceZapatillas.entity.Roles;
import pe.edu.ecommerceZapatillas.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {
    @Autowired
    MaintenanceService maintenanceService;


    /**
     * Usuarios
     */
    @GetMapping("/usuarios")
    public String vistaUsuarios(Model model) {
        model.addAttribute("usuarios", maintenanceService.getAllUsuarios());
        return "usuario/usuarios";
    }

    @GetMapping("/usuarios/{id}")
    public String vistaUsuarioDetalle(@PathVariable Integer id, Model model) {
        UsuariosDto usuariosDto = maintenanceService.getUsuarioById(id);
        model.addAttribute("usuariosDto", usuariosDto);
        return "usuario/usuarios-detail";
    }

    /** Usuarios Create
     *
     */
    @GetMapping("/usuariosRegister")
    public String vistaUsuarioCrear(Model model){
        UsuariosDto usuariosDto = new UsuariosDto(0,"","",2,"");
        List<RolesDto> roles =maintenanceService.getAllRoles();
        model.addAttribute("usuariosDto",usuariosDto);
        model.addAttribute("roles",roles);
        return "usuario/usuarios-register";
    }

    @PostMapping("/registerUsuario")
    public String createUsuario(UsuariosDto usuariosDto){
        maintenanceService.createUsuario(usuariosDto);
        return "redirect:/web/usuarios";
    }

    /** Usuarios Update
     *
     */
    @GetMapping("/usuariosUpdate/{id}")
    public String vistaUsuarioUpdate(@PathVariable Integer id, Model model) {
        UsuariosDto usuariosDto = maintenanceService.getUsuarioById(id);
        List<RolesDto> roles =maintenanceService.getAllRoles();
        model.addAttribute("usuariosDto", usuariosDto);
        model.addAttribute("roles",roles);

        return "usuario/usuarios-update";
    }

    @PostMapping("/updateUsuario")
    public String updateUsuario( UsuariosDto usuariosDto){
        maintenanceService.updateUsuario(usuariosDto);
        return "redirect:/web/usuarios";
    }

    /**
     * Usuarios Delete
     */
    @PostMapping("/deleteUsuario/{id}")
    public String deleteFilm(@PathVariable Integer id) {
        maintenanceService.deleteUsuario(id);
        return "redirect:/web/usuarios";
    }







    /**
     * Productos
     */
    @GetMapping("/productos")
    public String vistaProductos(Model model) {
        model.addAttribute("productos", maintenanceService.getAllProductos());
        return "producto/productos";
    }

    @GetMapping("/productos/{id}")
    public String vistaProductoDetalle(@PathVariable Integer id, Model model) {
        ProductosDto productosDto = maintenanceService.getProductoById(id);
        model.addAttribute("productosDto", productosDto);
        return "producto/productos-detail";
    }

    /** Productos Create
     *
     */
    @GetMapping("/productosRegister")
    public String vistaProductoCrear(Model model){
        ProductosDto productosDto = new ProductosDto(0,"","",0.0,0);
        model.addAttribute("productosDto",productosDto);
        return "producto/productos-register";
    }

    @PostMapping("/registerProducto")
    public String createProducto(ProductosDto productosDto){
        maintenanceService.createProducto(productosDto);
        return "redirect:/web/productos";
    }

    /** Productos Update
     *
     */
    @GetMapping("/productosUpdate/{id}")
    public String vistaProductoUpdate(@PathVariable Integer id, Model model) {
        ProductosDto productosDto = maintenanceService.getProductoById(id);
        model.addAttribute("productosDto", productosDto);
        return "producto/productos-update";
    }

    @PostMapping("/updateProducto")
    public String updateProducto( ProductosDto productosDto){
        maintenanceService.updateProducto(productosDto);
        return "redirect:/web/productos";
    }

    /**
     * Productos Delete
     */
    @PostMapping("/deleteProducto/{id}")
    public String deleteProducto(@PathVariable Integer id) {
        maintenanceService.deleteProducto(id);
        return "redirect:/web/productos";
    }











    /**
     * Ventas
     */
    @GetMapping("/ventas")
    public String vistaVentas(Model model) {
        model.addAttribute("ventas", maintenanceService.getAllVentas());
        return "venta/ventas";
    }

    @GetMapping("/ventas/{id}")
    public String vistaVentaDetalle(@PathVariable Integer id, Model model) {
        VentasDto ventasDto = maintenanceService.getVentaById(id);
        model.addAttribute("ventasDto", ventasDto);
        return "venta/venta-detail";
    }


    /** Ventas Update
     *
     */
    @GetMapping("/ventasUpdate/{id}")
    public String vistaVentaUpdate(@PathVariable Integer id, Model model) {
        VentasDto ventasDto = maintenanceService.getVentaById(id);
        model.addAttribute("ventasDto", ventasDto);
        return "venta/venta-update";
    }

    @PostMapping("/updateVentas")
    public String updateVenta( VentasDto ventasDto){
        maintenanceService.editarVenta(ventasDto);
        return "redirect:/web/ventas";
    }

    @GetMapping("/detalleVentas")
    public String vistaDetalleVenta(Model model){
        model.addAttribute("detallesVenta",maintenanceService.getAllDetallesVenta());
        return "detalleVenta/detalleVentas";
    }


}
