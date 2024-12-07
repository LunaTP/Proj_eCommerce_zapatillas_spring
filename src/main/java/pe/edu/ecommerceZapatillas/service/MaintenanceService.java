package pe.edu.ecommerceZapatillas.service;

import pe.edu.ecommerceZapatillas.dto.DetalleVentaDto;
import pe.edu.ecommerceZapatillas.dto.ProductosDto;
import pe.edu.ecommerceZapatillas.dto.RolesDto;
import pe.edu.ecommerceZapatillas.dto.UsuariosDto;

import java.util.List;

public interface MaintenanceService {

    /**
     * Roles
     */
    List<RolesDto> getAllRoles();

    /**
     * Usuarios
     */
    List<UsuariosDto> getAllUsuarios();

    UsuariosDto getUsuarioById(Integer id);

    void createUsuario(UsuariosDto usuariosDto);

    void updateUsuario(UsuariosDto usuariosDto);

    void deleteUsuario(Integer id);
    /**
     * Productos
     */
    List<ProductosDto> getAllProductos();

    ProductosDto getProductoById(Integer id);

    void createProducto(ProductosDto usuariosDto);

    void updateProducto(ProductosDto usuariosDto);

    void deleteProducto(Integer id);

    /**
     * DetalleVenta
     */
    List<DetalleVentaDto> getAllDetalleVenta();

}
