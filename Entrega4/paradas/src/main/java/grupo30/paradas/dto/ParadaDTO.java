package grupo30.paradas.dto;

import grupo30.paradas.entity.Parada;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ParadaDTO {
    private Long id;
    private String nombre;
    private Double latitud;
    private Double longitud;
    private String qr;
    private List<Long> disponibles;

    public ParadaDTO(Long id, String nombre, Double latitud, Double longitud, String qr, List<Long> disponibles) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.qr = qr;
        // si viene null, se inicializa con lista vacía
        this.disponibles = (disponibles != null) ? disponibles : new ArrayList<>();
    }

    public ParadaDTO(String nombre, Double latitud, Double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.qr = null;
        this.disponibles = new ArrayList<>();
    }

    public ParadaDTO(Parada p){
        this.id = p.getId();
        this.nombre = p.getNombre();
        this.latitud = p.getLatitud();
        this.longitud = p.getLongitud();
        this.qr = p.getQr();
        this.disponibles = p.getDisponibles();
    }

    public ParadaDTO(String nombre, Double latitud, Double longitud, String qr) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.qr = qr;
    }


    @Override
    public String toString() {
        return "ParadaDTO [" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", qr='" + qr + '\'' +
                ", disponibles=" + disponibles +
                ']';
    }
}
