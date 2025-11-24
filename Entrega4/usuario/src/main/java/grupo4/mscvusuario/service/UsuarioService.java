package grupo4.mscvusuario.service;

import grupo4.mscvusuario.dto.UsuarioDTO;
import grupo4.mscvusuario.entity.Usuario;
import grupo4.mscvusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public List<UsuarioDTO> findAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioDTOs.add(new UsuarioDTO(usuario));
        }
        return usuarioDTOs;
    }

    @Transactional
    public UsuarioDTO findById(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario == null ? null : new UsuarioDTO(usuario);
    }

    @Transactional
    public Usuario save(Usuario usuario){
        if(usuario.getPassword() != null) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }

    @Transactional
    public UsuarioDTO update(Usuario usuario) {
        Usuario userExistente = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (userExistente == null) {
            return null;
        }
        if(usuario.getUsuario() != null && !usuario.getUsuario().isEmpty()) {
            userExistente.setUsuario(usuario.getUsuario());
        }
        if(usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            userExistente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        if(usuario.getNombre() != null && !usuario.getNombre().isEmpty()) {
            userExistente.setNombre(usuario.getNombre());
        }
        if(usuario.getApellido() != null && !usuario.getApellido().isEmpty()) {
            userExistente.setApellido(usuario.getApellido());
        }
        if(usuario.getEmail() != null && !usuario.getEmail().isEmpty()) {
            userExistente.setEmail(usuario.getEmail());
        }
        if(usuario.getCelular() != null && !usuario.getCelular().isEmpty()) {
            userExistente.setCelular(usuario.getCelular());
        }
        if(usuario.getRol() != null && !usuario.getRol().equals(userExistente.getRol())) {
            userExistente.setRol(usuario.getRol());
        }
        if(usuario.isHabilitado() != userExistente.isHabilitado()) {
            userExistente.setHabilitado(usuario.isHabilitado());
        }
        if(usuario.getCuentas() != null && !usuario.getCuentas().equals(userExistente.getCuentas())) {
            userExistente.setCuentas(usuario.getCuentas());
        }
        Usuario actualizado = usuarioRepository.save(userExistente);
        return new UsuarioDTO(actualizado);
    }

    @Transactional(readOnly = true)
    public Usuario findEntityByUsuario(String username) {
        return usuarioRepository.findByUsuario(username).orElse(null);
    }
}
