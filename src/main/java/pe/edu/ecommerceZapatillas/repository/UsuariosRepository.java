package pe.edu.ecommerceZapatillas.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.ecommerceZapatillas.entity.Usuarios;

import java.util.Optional;

public interface UsuariosRepository extends CrudRepository<Usuarios,Integer> {
    Optional<Usuarios> findByEmail(String email);
}
