package pe.edu.ecommerceZapatillas.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.ecommerceZapatillas.dto.RolesDto;
import pe.edu.ecommerceZapatillas.dto.UsuariosDto;
import pe.edu.ecommerceZapatillas.entity.Usuarios;
import pe.edu.ecommerceZapatillas.service.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MaintenanceApi {

    @Autowired
    MaintenanceService maintenanceService;

    @GetMapping("/roles")
    public List<RolesDto> getAllRoles(){
        return maintenanceService.getAllRoles();
    }

    @GetMapping("/usuarios")
    public Object getUsuarios(@RequestParam(name = "id", required = false) Integer id) {
        if (id != null) {
            return maintenanceService.getUsuarioById(id);
        }
        return maintenanceService.getAllUsuarios();
    }

    @PostMapping("/ingreso")
    public String newUsuario(@RequestBody UsuariosDto usuariosDto) {
        maintenanceService.createUsuario(usuariosDto);
        return usuariosDto.toString();
    }
}
