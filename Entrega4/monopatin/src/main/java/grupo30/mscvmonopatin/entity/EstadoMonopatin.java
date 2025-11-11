package grupo30.mscvmonopatin.entity;

import java.util.Arrays;

public enum EstadoMonopatin {
        ESTACIONADO,
        VIAJE,
        PAUSA,
        MANTENIMIENTO,
        BAJA;

    public static boolean esValido(String estado) {
        return Arrays.stream(values())
                .anyMatch(e -> e.name().equalsIgnoreCase(estado));
    }

}
