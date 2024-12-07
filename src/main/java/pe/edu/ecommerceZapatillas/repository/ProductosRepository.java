package pe.edu.ecommerceZapatillas.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.ecommerceZapatillas.entity.Productos;

public interface ProductosRepository extends CrudRepository<Productos,Integer> {
}
