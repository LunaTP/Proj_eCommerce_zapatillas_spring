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
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String contrasenia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    private Roles rolId;

    @OneToMany(mappedBy = "usuarioId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Ventas> ventas;

}
