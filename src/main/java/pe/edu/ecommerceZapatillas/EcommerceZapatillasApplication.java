package pe.edu.ecommerceZapatillas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.edu.ecommerceZapatillas.dto.DetalleVentaDto;
import pe.edu.ecommerceZapatillas.dto.ProductosDto;
import pe.edu.ecommerceZapatillas.dto.RolesDto;
import pe.edu.ecommerceZapatillas.dto.UsuariosDto;
import pe.edu.ecommerceZapatillas.service.MaintenanceService;

import java.util.List;

@SpringBootApplication
public class EcommerceZapatillasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceZapatillasApplication.class, args);
	}

	@Autowired
	MaintenanceService maintenanceService;

	@Override
	public void run(String... args) throws Exception {
		List<DetalleVentaDto> detalleVentaDtos = maintenanceService.getAllDetalleVenta();
		List<RolesDto> rolesDtos = maintenanceService.getAllRoles();
		List<UsuariosDto> usuariosDtos = maintenanceService.getAllUsuarios();
		List<ProductosDto> productosDtos = maintenanceService.getAllProductos();

		rolesDtos.forEach(System.out::println);
		usuariosDtos.forEach(System.out::println);
		productosDtos.forEach(System.out::println);
		detalleVentaDtos.forEach(System.out::println);


	}
}
