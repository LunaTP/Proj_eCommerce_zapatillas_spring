package pe.edu.ecommerceZapatillas.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.ecommerceZapatillas.dto.*;
import pe.edu.ecommerceZapatillas.entity.*;
import pe.edu.ecommerceZapatillas.repository.*;
import pe.edu.ecommerceZapatillas.service.MaintenanceService;

import java.util.ArrayList;
import java.util.Date;
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
    VentasRepository ventasRepository;

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
    public List<UsuariosDetailDto> getAllUsuarios() {
        List<UsuariosDetailDto> lista = new ArrayList<>();
        Iterable<Usuarios> usuarios = usuariosRepository.findAll();

        usuarios.forEach(item ->{
            UsuariosDetailDto dto = new UsuariosDetailDto(
                    item.getId(),
                    item.getNombre(),
                    item.getEmail(),
                    item.getRolId().getNombre()
            );
            lista.add(dto);
        });
        System.out.println("Fin del listado de Usuarios");
        return lista;
    }

    @Override
    public UsuariosDto getUsuarioById(Integer id) {
        Optional<Usuarios> usuarios =usuariosRepository.findById(id);
        Usuarios usu = usuarios.get();

        System.out.println("Se busco exitosamente el Id "+ id);
        return usuarios.map(item -> new UsuariosDto(
                item.getId(),
                item.getNombre(),
                item.getEmail(),
                usu.getRolId().getId(),
                item.getContrasenia()
        )).orElse(null);

    }

    @Override
    public void createUsuario(UsuariosDto usuariosDto) {
        Optional<Roles> roles = rolesRepository.findById(usuariosDto.rolId());
        Roles rol = roles.get();

        Usuarios usuarios = new Usuarios();
        usuarios.setNombre(usuariosDto.nombre());
        usuarios.setEmail(usuariosDto.email());
        usuarios.setContrasenia(usuariosDto.contrasenia());
        usuarios.setRolId(rol);

        usuariosRepository.save(usuarios);

        System.out.println("Se creo un nuevo Usuario");
    }

    @Override
    public void updateUsuario(UsuariosDto usuariosDto) {
        Optional<Usuarios> optionalUsuarios = usuariosRepository.findById(usuariosDto.id());
        Usuarios usuarios = optionalUsuarios.get();
        Optional<Roles> roles = rolesRepository.findById(usuariosDto.rolId());
        Roles rol = roles.get();

        usuarios.setNombre(usuariosDto.nombre());
        usuarios.setEmail(usuariosDto.email());
        usuarios.setContrasenia(usuariosDto.contrasenia());
        usuarios.setRolId(rol);

        usuariosRepository.save(usuarios);

        System.out.println("Se actualizo el usuario");
    }

    @Override
    public void deleteUsuario(Integer id) {
        usuariosRepository.deleteById(id);
        System.out.println("Se elimino el usuario: " + id);
    }

    @Override
    public UsuarioLoginDto login(String email, String contrasenia) {

        Usuarios usuarios = usuariosRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if(!usuarios.getContrasenia().equals(contrasenia)) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        return new UsuarioLoginDto(usuarios.getEmail(), usuarios.getContrasenia());
    }


    /**
     * Productos
     */
    @Override
    public List<ProductosDetailDto> getAllProductos() {
        List<ProductosDetailDto> list = new ArrayList<>();
        Iterable<Productos> productos = productosRepository.findAll();

        productos.forEach(item ->{
            ProductosDetailDto dto = new ProductosDetailDto(
                    item.getId(),
                    item.getNombre(),
                    item.getDescripcion(),
                    item.getPrecio());
            list.add(dto);
        });
        System.out.println("Fin del listado de Productos");
        return list;
    }

    @Override
    public ProductosDto getProductoById(Integer id) {
        Optional<Productos> productos =productosRepository.findById(id);

        System.out.println("Se busco exitosamente el producto Id: " +id);

        return productos.map(item -> new ProductosDto(
                item.getId(),
                item.getNombre(),
                item.getDescripcion(),
                item.getPrecio(),
                item.getStock()
        )).orElse(null);
    }

    @Override
    public void createProducto(ProductosDto usuariosDto) {

    }

    @Override
    public void updateProducto(ProductosDto usuariosDto) {

    }

    @Override
    public void deleteProducto(Integer id) {
        productosRepository.deleteById(id);
    }

    /**
     * Ventas
     */
    @Override
    public void registrarVenta(VentasDto ventasDto) {
        Ventas venta = new Ventas();
        venta.setFecha(new Date());

        Optional<Usuarios> usuario = usuariosRepository.findById(ventasDto.usuarioId());
        usuario.ifPresent(venta::setUsuarioId);

        Ventas ventaGuardada = ventasRepository.save(venta);

        List<DetallesVenta> listaDetallesVentas = new ArrayList<>();
        for(DetalleVentaDto dto : ventasDto.detalles()){
            DetallesVenta detallesVenta = new DetallesVenta();
            detallesVenta.setCantidad(dto.cantidad());

            Optional<Productos> optionalProducto = productosRepository.findById(dto.productoId());
            optionalProducto.ifPresent(detallesVenta::setProductoId);
            detallesVenta.setPrecioUnitario(optionalProducto.get().getPrecio());

            detallesVenta.setVentaId(ventaGuardada);

            listaDetallesVentas.add(detallesVenta);
        }
        detallesVentasRepository.saveAll(listaDetallesVentas);
    }


    @Override
    public List<DetalleVentaDto> getAllDetallesVenta() {
        List<DetalleVentaDto> list = new ArrayList<>();
        Iterable<DetallesVenta> detallesVentas = detallesVentasRepository.findAll();

        detallesVentas.forEach(item ->{
            DetalleVentaDto dto = new DetalleVentaDto(
                    item.getVentaId().getId(),
                    item.getId(),
                    item.getCantidad(),
                    item.getPrecioUnitario(),
                    item.getPrecioUnitario()*item.getCantidad());
            list.add(dto);
        });
        System.out.println("Fin del listado de Productos");
        return list;
    }


    @Override
    public List<DetalleVentaDto> getDetallesVentaByVentaId(Integer ventaId) {
        List<DetalleVentaDto> list = new ArrayList<>();
        List<DetallesVenta> detallesVentas = detallesVentasRepository.findByVentaId_Id(ventaId);

        detallesVentas.forEach(item -> {
            DetalleVentaDto dto = new DetalleVentaDto(
                    item.getVentaId().getId(),
                    item.getProductoId().getId(),
                    item.getCantidad(),
                    item.getPrecioUnitario(),
                    item.getPrecioUnitario() * item.getCantidad()
            );
            list.add(dto);
        });
        return list;
    }

}