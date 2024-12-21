package pe.edu.ecommerceZapatillas.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import pe.edu.ecommerceZapatillas.dto.*;

import java.util.List;
public interface MaintenanceService {
    /**
     * Roles
     */
    @Cacheable(value = "Roles")
    List<RolesDto> getAllRoles();

    /**
     * Usuarios
     */
    @Cacheable(value = "usuarios")
    List<UsuariosDetailDto> getAllUsuarios();

    @Cacheable(value = "usuarios")
    UsuariosDto getUsuarioById(Integer id);

    @CacheEvict(value = "usuarios", allEntries = true )
    void createUsuario(UsuariosDto usuariosDto);

    @CacheEvict(value = "usuarios", allEntries = true )
    void updateUsuario(UsuariosDto usuariosDto);


    @CacheEvict(value = "usuarios", allEntries = true )
    void deleteUsuario(Integer id);

    @Cacheable(value = "usuarios")
    UsuarioLoginDto login(String email, String contrasenia);

    /**
     * Productos
     */
    @Cacheable(value = "Productos")
    List<ProductosDetailDto> getAllProductos();

    @Cacheable(value = "Productos")
    ProductosDto getProductoById(Integer id);

    @CacheEvict(value = "Productos", allEntries = true )
    void createProducto(ProductosDto usuariosDto);

    @CacheEvict(value = "Productos", allEntries = true )
    void updateProducto(ProductosDto usuariosDto);

    @CacheEvict(value = "Productos", allEntries = true )
    void deleteProducto(Integer id);

    /**
     * Detalles Ventas
     */
    List<DetalleVentaDetailDto> getAllDetallesVenta();

    /**
     * Ventas
     */
    @Cacheable(value = "Ventas")
    List<VentasDetailDto> getAllVentas();

    @Cacheable(value = "Ventas")
    VentasDto getVentaById(Integer id);

    @CacheEvict(value = "Ventas", allEntries = true )
    void editarVenta(VentasDto ventasDto);

    @CacheEvict(value = "Ventas", allEntries = true )
    void registrarVenta(VentasDto ventasDto);






    @Cacheable(value = "Ventas")
    UsuariosDto getUsuarioByEmail(String email);

}