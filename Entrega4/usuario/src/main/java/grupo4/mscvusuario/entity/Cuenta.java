package grupo4.mscvusuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;

    private TipoDeCuenta tipoCuenta;
    @Column(name = "km_recorridos")
    private Double kmRecorridos;
    @Column(name = "fecua_limite_km")
    private LocalDate fecuaLimiteKm;

    @ManyToMany( mappedBy = "cuentas")
    private List<Usuario> usuarios;
}
