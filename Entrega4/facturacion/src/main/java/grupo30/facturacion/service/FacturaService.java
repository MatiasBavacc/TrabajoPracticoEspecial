package grupo30.facturacion.service;

import grupo30.facturacion.client.TarifaFeignClient;
import grupo30.facturacion.client.UsuarioFeignClient;
import grupo30.facturacion.entity.Cuenta;
import grupo30.facturacion.entity.Factura;

import grupo30.facturacion.entity.Usuario;
import grupo30.facturacion.entity.Viaje;
import grupo30.facturacion.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private TarifaFeignClient tarifaFeignClient;

    @Autowired
    private UsuarioFeignClient usuarioFeignClient;


    @Transactional(readOnly = true)
    public List<Factura> findAll(){
        return facturaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Factura findById(String id){
        return facturaRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Factura> findByUsuarioId(Long id){
        return facturaRepository.findByUsuario_Id(id);
    }


    @Transactional
    public Factura save(Factura factura){
        return facturaRepository.save(factura);
    }

    @Transactional
    public Factura crearFactura(Viaje viaje){
        Usuario usuario = usuarioFeignClient.getUsuario(viaje.getUsuario_id());
        Cuenta cuenta = usuarioFeignClient.getCuenta(viaje.getCuenta_id());
        Factura factura = new Factura(usuario, cuenta, viaje);

        System.out.println(factura.toString());


        return facturaRepository.save(factura);
    }

    @Transactional
    public List<Factura> findByFechaBetween(Date fecha1, Date fecha2) {
        LocalDate inicio = fecha1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fecha2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return facturaRepository.findByFechaBetween(inicio, fin);
    }

    // Aquí puedes usar tarifaFeignClient cuando necesites consultar tarifas
    // Ejemplo: TarifaDTO tarifa = tarifaFeignClient.findTarifaById(id).getBody();
}
