package pe.edu.ecommerceZapatillas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.ecommerceZapatillas.dto.*;
import pe.edu.ecommerceZapatillas.service.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    /**
     * Usuarios
     */
    //Lista de Usuarios
    @GetMapping("/indexUsuarios")
    public List<UsuariosDetailDto> listUsuarios(){
        return maintenanceService.getAllUsuarios();
    }
    //Get Usuario por Id
    @GetMapping("/usuario/{id}")
    public UsuariosDto getUsuario(@PathVariable Integer id){
        return maintenanceService.getUsuarioById(id);
    }
    //Create Usuario
    @PostMapping("/registerUsuario")
    public ResponseEntity<String> createUsuario(@RequestBody UsuariosDto usuariosDto){
        try {
            maintenanceService.createUsuario(usuariosDto);
            return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el usuario: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    //Lista de Productos
    @GetMapping("/indexProductos")
    public List<ProductosDetailDto> listProductos(){
        return maintenanceService.getAllProductos();
    }
    //Get Producto por Id
    @GetMapping("/producto/{id}")
    public ProductosDto getProducto(@PathVariable Integer id){
        return maintenanceService.getProductoById(id);
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
