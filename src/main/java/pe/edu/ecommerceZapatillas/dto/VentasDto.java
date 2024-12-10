package pe.edu.ecommerceZapatillas.dto;

import java.util.Date;
import java.util.List;

/**
 * La LISTA llegaria a ser el carrito
 * y el USUARIOID seria quien hizo la compra
 */
public record VentasDto(Integer id, Date fecha, Integer usuarioId, List<DetalleVentaDto> detalles) {
}
