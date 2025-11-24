package grupo30.viajes.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import grupo30.viajes.entity.Viaje;
import grupo30.viajes.repository.ViajeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ViajeRepository viajeRepository) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            // Registrar soporte para LocalDateTime
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            TypeReference<List<Viaje>> typeReference = new TypeReference<>() {};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/import.json");

            if (inputStream != null) {
                List<Viaje> viajes = mapper.readValue(inputStream, typeReference);
                viajeRepository.deleteAll(); // opcional: limpiar antes
                viajeRepository.saveAll(viajes);
                System.out.println("✅ Datos cargados desde resources/import.json");
            } else {
                System.out.println("⚠️ No se encontró el archivo import.json en resources");
            }
        };
    }
}
