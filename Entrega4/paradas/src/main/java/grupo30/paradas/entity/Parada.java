package grupo30.paradas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "parada")
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double latitud;
    private Double longitud;

    @Column(nullable = true)
    private String qr;

    @ElementCollection
    @CollectionTable(
            name = "parada_disponibles",
            joinColumns = @JoinColumn(name = "parada_id")
    )
    @Column(name = "monopatin_id")
    private List<Long> disponibles = new ArrayList<>();

    public Parada(String nombre, Double latitud, Double longitud, String qr) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.qr = qr;
    }

    public Parada(String nombre, Double latitud, Double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}
