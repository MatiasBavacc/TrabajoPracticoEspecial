package grupo4.facturacion.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double monto;

    @Enumerated(EnumType.STRING)
    private TipoDeCuenta tipoCuenta;
    @Column(name = "km_recorridos")
    private Double kmRecorridos;
    @Column(name = "fecua_limite_km")
    private LocalDate fecuaLimiteKm;

    @ManyToMany( mappedBy = "cuentas")
    private List<Usuario> usuarios;
}
