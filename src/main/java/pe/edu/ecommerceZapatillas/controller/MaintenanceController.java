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
@RequestMapping("/maintenance")
public class MaintenanceController {

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

    //Delete Usuario
    @PostMapping("/deleteUsuario/{id}")
    public void deleteUsuario(@PathVariable Integer id){
        maintenanceService.deleteUsuario(id);
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





    /**
     * DetallesVenta
     */
    //Lista los Detalles de Venta
    @GetMapping("/indexDetalleVenta")
    public List<DetalleVentaDto> listDetalleVenta(){
        return maintenanceService.getAllDetallesVenta();
    }
    //Obtener los Detalles de Venta por id de la venta
    @GetMapping("/detallesVenta/{id}")
    public ResponseEntity<List<DetalleVentaDto>> getDetallesVentaByVentaId(@PathVariable Integer id) {
        try {
            List<DetalleVentaDto> detalles = maintenanceService.getDetallesVentaByVentaId(id);
            return new ResponseEntity<>(detalles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
