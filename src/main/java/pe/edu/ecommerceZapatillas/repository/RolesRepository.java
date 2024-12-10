package pe.edu.ecommerceZapatillas.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.ecommerceZapatillas.entity.Roles;

public interface RolesRepository extends CrudRepository<Roles,Integer> {
    Integer id(Integer id);
}
