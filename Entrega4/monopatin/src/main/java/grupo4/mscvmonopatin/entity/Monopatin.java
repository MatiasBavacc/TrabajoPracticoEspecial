package grupo4.mscvmonopatin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "monopatin")
public class Monopatin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patente;

    private Double latitud;
    private Double longitud;

    private int km;
    private Double horas;

    @Enumerated(EnumType.STRING)
    private EstadoMonopatin estadoMonopatin;

    public Monopatin(String patente, Double latitud, Double longitud, int km, Double horas, EstadoMonopatin estadoMonopatin) {
        this.patente = patente;
        this.latitud = latitud;
        this.longitud = longitud;
        this.km = km;
        this.horas = horas;
        this.estadoMonopatin = estadoMonopatin;
    }
}
