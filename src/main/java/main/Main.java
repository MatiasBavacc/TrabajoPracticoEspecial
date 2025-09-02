package main;

import factory.BaseDeDatosFactory;
import utils.ConvertClient;

public class Main {
    private static final String CLIENTESCSV = "src/main/resources/csv/clientes.csv";

    public static void main (String[] params) throws Exception {
        BaseDeDatosFactory mysql = BaseDeDatosFactory.getFactory(BaseDeDatosFactory.MYSQL_JDBC);
        mysql.getProductoDao().createTable();
        mysql.getProductoDao().getTable();
        mysql.getProductoDao();

        ConvertClient nuevo = new ConvertClient();
        System.out.println(nuevo.convertCsv(CLIENTESCSV));

        mysql.closeConection();

    }
}
