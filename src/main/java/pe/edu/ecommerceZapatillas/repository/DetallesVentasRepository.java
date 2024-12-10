package pe.edu.ecommerceZapatillas.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.ecommerceZapatillas.entity.DetallesVenta;

import java.util.List;

public interface DetallesVentasRepository extends CrudRepository<DetallesVenta,Integer> {
    List<DetallesVenta> findByVentaId_Id(Integer ventaId);

}
