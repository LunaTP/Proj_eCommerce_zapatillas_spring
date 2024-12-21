package pe.edu.ecommerceZapatillas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.ecommerceZapatillas.dto.*;
import pe.edu.ecommerceZapatillas.service.MaintenanceService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    MaintenanceService maintenanceService;

    /**
     * Usuarios
     */
    //Lista de Usuarios
    @GetMapping("/usuarios")
    public Object getUsuarios(@RequestParam(name = "id", required = false) Integer id) {
        if (id != null) {
            return maintenanceService.getUsuarioById(id);
        }
        return maintenanceService.getAllUsuarios();
    }


    //Create Usuario
    @PostMapping("/registerUsuario")
    public String createUsuario(@RequestBody UsuariosDto usuariosDto){
            maintenanceService.createUsuario(usuariosDto);
            return usuariosDto.toString();
    }
    //Update Usuario
    @PostMapping("/updateUsuario")
    public String updateUsuario(@RequestBody UsuariosDto usuariosDto){
        maintenanceService.updateUsuario(usuariosDto);
        return usuariosDto.toString();
    }

    //Delete Usuario
    @PostMapping("/deleteUsuario")
    public void deleteUsuario(@RequestParam Integer id) {
        maintenanceService.deleteUsuario(id);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto usuarioLoginDto){
        try {
            UsuarioLoginDto usuarioLoginDto1 = maintenanceService.login(usuarioLoginDto.email(), usuarioLoginDto.contrasenia());
            return new ResponseEntity<>(usuarioLoginDto1, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getUserBySession")
    public Object getUsuarioForSession(@RequestParam(name = "email", required = false) String email){
        return maintenanceService.getUsuarioByEmail(email);
    }




    /**
     * Productos
     */
    //Lista de Productos    //Get Producto por Id
    @GetMapping("/productos")
    public Object getProducto(@RequestParam(name = "id", required = false) Integer id){
        if (id != null) {
            return maintenanceService.getProductoById(id);
        }
        return maintenanceService.getAllProductos();
    }



    /**
     * Venta
     */
    //Registra Venta
    @PostMapping("/registrarVenta")
    public ResponseEntity<String> registrarVenta(@RequestBody VentasDto ventasDto){
        try {
            maintenanceService.registrarVenta(ventasDto);
            return new ResponseEntity<>("Venta registrada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar la venta: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






}
