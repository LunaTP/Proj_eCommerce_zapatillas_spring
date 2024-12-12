package pe.edu.ecommerceZapatillas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.ecommerceZapatillas.dto.ProductosDto;
import pe.edu.ecommerceZapatillas.dto.RolesDto;
import pe.edu.ecommerceZapatillas.dto.UsuariosDto;
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
        return "usuarios";
    }

    @GetMapping("/usuarios/{id}")
    public String vistaUsuarioDetalle(@PathVariable Integer id, Model model) {
        UsuariosDto usuariosDto = maintenanceService.getUsuarioById(id);
        model.addAttribute("usuariosDto", usuariosDto);
        return "usuarios-detail";
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
        return "usuarios-register";
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

        return "usuarios-update";
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
        return "productos";
    }

    @GetMapping("/productos/{id}")
    public String vistaProductoDetalle(@PathVariable Integer id, Model model) {
        ProductosDto productosDto = maintenanceService.getProductoById(id);
        model.addAttribute("productosDto", productosDto);
        return "productos-detail";
    }

    /** Productos Create
     *
     */
    @GetMapping("/productosRegister")
    public String vistaProductoCrear(Model model){
        ProductosDto productosDto = new ProductosDto(0,"","",0.0,0);
        model.addAttribute("productosDto",productosDto);
        return "productos-register";
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
        return "usuarios-update";
    }

    @PostMapping("/updateProducto")
    public String updateProducto( UsuariosDto usuariosDto){
        maintenanceService.updateUsuario(usuariosDto);
        return "redirect:/web/usuarios";
    }

}
