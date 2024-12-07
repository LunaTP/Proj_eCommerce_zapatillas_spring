package pe.edu.ecommerceZapatillas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fecha;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuarios usuarioId;

    @OneToMany(mappedBy = "ventaId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<DetallesVenta> detallesVentas;


}
