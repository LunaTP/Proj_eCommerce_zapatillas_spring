package pe.edu.ecommerceZapatillas.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.ecommerceZapatillas.dto.DetalleVentaDto;
import pe.edu.ecommerceZapatillas.dto.ProductosDto;
import pe.edu.ecommerceZapatillas.dto.RolesDto;
import pe.edu.ecommerceZapatillas.dto.UsuariosDto;
import pe.edu.ecommerceZapatillas.entity.DetallesVenta;
import pe.edu.ecommerceZapatillas.entity.Productos;
import pe.edu.ecommerceZapatillas.entity.Roles;
import pe.edu.ecommerceZapatillas.entity.Usuarios;
import pe.edu.ecommerceZapatillas.repository.DetallesVentasRepository;
import pe.edu.ecommerceZapatillas.repository.ProductosRepository;
import pe.edu.ecommerceZapatillas.repository.RolesRepository;
import pe.edu.ecommerceZapatillas.repository.UsuariosRepository;
import pe.edu.ecommerceZapatillas.service.MaintenanceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    ProductosRepository productosRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    DetallesVentasRepository detallesVentasRepository;


    /**
     * Roles
     */
    @Override
    public List<RolesDto> getAllRoles() {
        List<RolesDto> rolesDtos = new ArrayList<>();
        Iterable<Roles> roles = rolesRepository.findAll();

        roles.forEach(item ->{
            RolesDto rolesDto = new RolesDto(item.getNombre());
            rolesDtos.add(rolesDto);
        });
        System.out.println("Fin del listado de roles");
        return rolesDtos;
    }

    /**
     * Usuarios
     */
    @Override
    public List<UsuariosDto> getAllUsuarios() {
        List<UsuariosDto> usuariosDtos = new ArrayList<>();
        Iterable<Usuarios> usuarios = usuariosRepository.findAll();

        usuarios.forEach(item ->{
            UsuariosDto dto = new UsuariosDto(
                    item.getNombre(),
                    item.getEmail(),
                    item.getRolId().getId(),
                    item.getContrasenia()
            );
            usuariosDtos.add(dto);
        });
        System.out.println("Fin del listado de Usuarios");
        return usuariosDtos;
    }

    @Override
    public UsuariosDto getUsuarioById(Integer id) {
        Optional<Usuarios> optional = usuariosRepository.findById(id);
        return optional.map(
                usuarios -> new UsuariosDto(
                        usuarios.getNombre(),
                        usuarios.getEmail(),
                        usuarios.getRolId().getId(),
                        usuarios.getContrasenia()
                )
        ).orElse(null);
    }

    @Override
    public void createUsuario(UsuariosDto usuariosDto) {

        Integer rolId = (usuariosDto.rol() != null) ? usuariosDto.rol() : 2;

        Roles rol = rolesRepository.findById(rolId)
                .orElseThrow(() -> new RuntimeException("Rol con ID " + rolId + " no encontrado"));

        Usuarios usuarios = new Usuarios();
        usuarios.setNombre(usuariosDto.nombre());
        usuarios.setEmail(usuariosDto.email());
        usuarios.setRolId(rol);
        usuarios.setContrasenia(usuariosDto.contrasenia());

        usuariosRepository.save(usuarios);
    }

    @Override
    public void updateUsuario(UsuariosDto usuariosDto) {

    }

    @Override
    public void deleteUsuario(Integer id) {

    }

    /**
     * Productos
     */
    @Override
    public List<ProductosDto> getAllProductos() {
        List<ProductosDto> productosDtos = new ArrayList<>();
        Iterable<Productos> productos = productosRepository.findAll();

        productos.forEach(item ->{
            ProductosDto dto = new ProductosDto(
                    item.getNombre(),
                    item.getDescripcion(),
                    item.getPrecio(),
                    item.getStock());
            productosDtos.add(dto);
        });
        System.out.println("Fin del listado de Productos");
        return productosDtos;
    }

    @Override
    public ProductosDto getProductoById(Integer id) {
        return null;
    }

    @Override
    public void createProducto(ProductosDto usuariosDto) {

    }

    @Override
    public void updateProducto(ProductosDto usuariosDto) {

    }

    @Override
    public void deleteProducto(Integer id) {

    }

    /**
     * DetalleVenta
     */
    @Override
    public List<DetalleVentaDto> getAllDetalleVenta() {
        List<DetalleVentaDto> detalleVentaDtos = new ArrayList<>();
        Iterable<DetallesVenta> detallesVentas = detallesVentasRepository.findAll();

        detallesVentas.forEach(item ->{
            DetalleVentaDto dto = new DetalleVentaDto(
                    item.getVentaId().getUsuarioId().getNombre(),
                    item.getProductoId().getNombre(),
                    item.getCantidad(),
                    item.getPrecioUnitario());
            detalleVentaDtos.add(dto);
        });
        System.out.println("Fin del listado de Productos");
        return detalleVentaDtos;
    }
}
