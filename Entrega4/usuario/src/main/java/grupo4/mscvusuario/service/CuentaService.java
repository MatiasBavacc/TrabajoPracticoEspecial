package grupo4.mscvusuario.service;

import grupo4.mscvusuario.dto.CuentaDTO;
import grupo4.mscvusuario.entity.Cuenta;
import grupo4.mscvusuario.entity.Usuario;
import grupo4.mscvusuario.repository.CuentaRepository;
import grupo4.mscvusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public List<CuentaDTO> findCuentaAll() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        List<CuentaDTO> cuentasDTO = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            cuentasDTO.add(new CuentaDTO(cuenta));
        }

        return cuentasDTO;
    }

    @Transactional
    public CuentaDTO findCuentaById(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
        if (cuenta == null) {
            return null;
        }
        return new CuentaDTO(cuenta);
    }

    @Transactional
    public List<CuentaDTO> findCuentaByUser(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null || usuario.getCuentas().isEmpty()) {
            return null;
        }

        List<CuentaDTO> cuentasDTO = new ArrayList<>();

        for (Cuenta c : usuario.getCuentas()) {
            cuentasDTO.add(new CuentaDTO(c));
        }

        return cuentasDTO;
    }

    @Transactional
    public CuentaDTO saveCuenta(Cuenta c) {
        Cuenta nueva =  cuentaRepository.save(c);
        return new CuentaDTO(nueva);
    }

    @Transactional
    public void deleteCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id).orElse(null);

        for(Usuario u : cuenta.getUsuarios()){
            u.getCuentas().remove(cuenta);
            usuarioRepository.save(u);
        }

        cuentaRepository.deleteById(id);
    }

    @Transactional
    public Object updateCuenta(Cuenta c) {
        Cuenta cuentaExistente = cuentaRepository.findById(c.getId()).orElse(null);
        if (cuentaExistente == null) {
            return null;
        }

        if(c.getMonto() != null && c.getMonto() >= 0){
            cuentaExistente.setMonto(c.getMonto());
        }

        if(c.getTipoCuenta() != null && !c.getTipoCuenta().equals(cuentaExistente.getTipoCuenta())){
            cuentaExistente.setTipoCuenta(c.getTipoCuenta());
        }

        if(c.getKmRecorridos() != null && c.getKmRecorridos() >= 0){
            cuentaExistente.setKmRecorridos(c.getKmRecorridos());
        }

        if(c.getFecuaLimiteKm() != null && !c.getFecuaLimiteKm().equals(cuentaExistente.getFecuaLimiteKm())){
            cuentaExistente.setFecuaLimiteKm(c.getFecuaLimiteKm());
        }

        if(c.getUsuarios() != null){
            cuentaExistente.setUsuarios(c.getUsuarios());
        }


        Cuenta cuentaActualizada = cuentaRepository.save(cuentaExistente);
        return new CuentaDTO(cuentaActualizada);
    }
}

