package grupo4.mscvusuario.dto;

import grupo4.mscvusuario.entity.Rol;
import grupo4.mscvusuario.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTOAux {
        private Long id;
        private String usuario;
        private String nombre;
        private String email;
        private String celular;
        private Boolean estado;
        private Rol rol;

        public UsuarioDTOAux(Usuario u) {
            id = u.getId();
            usuario = u.getUsuario();
            nombre = u.getUsuario();
            email= u.getEmail();
            celular = u.getCelular();
            rol = u.getRol();
            estado = u.isHabilitado();
        }
}
