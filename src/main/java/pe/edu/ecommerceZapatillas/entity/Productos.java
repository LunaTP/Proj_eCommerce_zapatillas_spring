package pe.edu.ecommerceZapatillas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    @OneToMany(mappedBy = "productoId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<DetallesVenta> detallesVentas;
}
