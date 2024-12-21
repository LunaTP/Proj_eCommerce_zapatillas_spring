package pe.edu.ecommerceZapatillas.service;

import pe.edu.ecommerceZapatillas.dto.*;

import java.util.List;

public interface MaintenanceService {

    /**
     * Roles
     */
    List<RolesDto> getAllRoles();

    /**
     * Usuarios
     */
    List<UsuariosDetailDto> getAllUsuarios();

    UsuariosDto getUsuarioById(Integer id);

    void createUsuario(UsuariosDto usuariosDto);

    void updateUsuario(UsuariosDto usuariosDto);


    void deleteUsuario(Integer id);

    UsuarioLoginDto login(String email, String contrasenia);

    /**
     * Productos
     */
    List<ProductosDetailDto> getAllProductos();

    ProductosDto getProductoById(Integer id);

    void createProducto(ProductosDto usuariosDto);

    void updateProducto(ProductosDto usuariosDto);

    void deleteProducto(Integer id);

    /**
     * Detalles Ventas
     */
    List<DetalleVentaDetailDto> getAllDetallesVenta();

    /**
     * Ventas
     */
    List<VentasDetailDto> getAllVentas();

    VentasDto getVentaById(Integer id);

    void editarVenta(VentasDto ventasDto);

    void registrarVenta(VentasDto ventasDto);






    UsuariosDto getUsuarioByEmail(String email);

}
