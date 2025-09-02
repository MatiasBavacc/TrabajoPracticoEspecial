package utils;

import entities.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ConvertClient {

    public List<Cliente> convertCsv(String ruta) throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(ruta));
        ArrayList<Cliente> salida = new ArrayList<>();
        for(CSVRecord row: parser) {
            int id = parseInt(row.get("idCliente"));
            String nombre = row.get("nombre");
            String email = row.get("email");
            Cliente nuevo = new Cliente(id, nombre, email);
            salida.add(nuevo);
        }
        return salida;
    }
}
