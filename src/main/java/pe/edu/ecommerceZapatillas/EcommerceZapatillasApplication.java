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
public class EcommerceZapatillasApplication{

	public static void main(String[] args) {
		SpringApplication.run(EcommerceZapatillasApplication.class, args);
	}

}
