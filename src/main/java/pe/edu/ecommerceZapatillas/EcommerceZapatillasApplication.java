package pe.edu.ecommerceZapatillas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pe.edu.ecommerceZapatillas.entity.DetallesVenta;
import pe.edu.ecommerceZapatillas.entity.Usuarios;
import pe.edu.ecommerceZapatillas.repository.DetallesVentasRepository;
import pe.edu.ecommerceZapatillas.repository.UsuariosRepository;

import java.util.List;

@SpringBootApplication
public class EcommerceZapatillasApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceZapatillasApplication.class, args);
	}

	@Autowired
	DetallesVentasRepository detallesVentasRepository;

	@Override
	public void run(String... args) throws Exception {
		detallesVentasRepository.findAll().forEach(System.out::println);

	}
}
