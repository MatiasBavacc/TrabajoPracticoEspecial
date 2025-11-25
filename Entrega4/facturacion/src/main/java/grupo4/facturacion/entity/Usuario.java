package grupo4.facturacion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String usuario;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private boolean habilitado;

    @ManyToMany
    @JoinTable(
            name = "usuario_cuentas",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "cuenta_id")
    )
    List<Cuenta> cuentas;
}
