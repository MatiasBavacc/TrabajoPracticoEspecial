package grupo30.mscvmercadopago.service;

import grupo30.mscvmercadopago.entity.MercadoCuenta;
import grupo30.mscvmercadopago.repository.MercadoPagoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import grupo30.mscvmercadopago.dto.MercadoCuentaDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class MercadoPagoService {
    @Autowired
    private MercadoPagoRepository mpRepository;

    /* endpoint todas ok  */
    @Transactional
    public List<MercadoCuentaDTO> findAll() {
        List<MercadoCuenta> nuevo = mpRepository.findAll();
        List<MercadoCuentaDTO> salida = new ArrayList<>();
        for(MercadoCuenta mc : nuevo){
            salida.add(new MercadoCuentaDTO(mc));
        }
        return salida;
    }

    /* endpoint una ok  */
    @Transactional
    public MercadoCuentaDTO findById(String id) {
        MercadoCuenta actual = mpRepository.findByIdUser(id);

        if (actual == null) {
            return null;
        }
        return new MercadoCuentaDTO(actual);
    }


    /* endpoint modificar falta  */
    @Transactional
    public MercadoCuentaDTO actualizarParada(MercadoCuentaDTO cuenta) {
        MercadoCuenta existente = mpRepository.findByIdUser(cuenta.getUserId());

        if(existente == null){
            return null;
        }

        if(cuenta.getSaldo() != null && cuenta.getSaldo() >= 0){
            if( existente.getSaldo() > 0 && existente.getSaldo() >= cuenta.getSaldo())
                existente.setSaldo(existente.getSaldo() - cuenta.getSaldo() );
        }

        MercadoCuentaDTO salida = new MercadoCuentaDTO( mpRepository.save(existente) );
        salida.setMoviento(cuenta.getSaldo());
        return salida;
    }




}
