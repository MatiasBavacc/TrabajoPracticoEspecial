package main;

import factory.BaseDeDatosFactory;
import utils.ConvertCliente;
import utils.ConvertDetalle;
import utils.ConvertFactura;
import utils.ConvertProducto;

public class Main {

    // Types of databases:  MYSQL_JDBC  DERBY_JDBC  POSTGRES_JDBC
    //private static BaseDeDatosFactory factory = BaseDeDatosFactory.getFactory(BaseDeDatosFactory.MYSQL_JDBC);
    private static BaseDeDatosFactory factory = BaseDeDatosFactory.getFactory(BaseDeDatosFactory.DERBY_JDBC);
    //private static BaseDeDatosFactory factory = BaseDeDatosFactory.getFactory(BaseDeDatosFactory.POSTGRES_JDBC);

    // DAO for table
    //private static ClienteDaoInterface clienteDAO;
    //private static FacturaDaoInterface facturaDAO;
    //private static DetalleDaoInterface detalleDAO;
    //private static ProductoDaoInterface productoDAO;

    // Archives Excel
    private static final String CLIENTESCSV = "src/main/resources/csv/clientes.csv";
    private static final String FACTURASCSV = "src/main/resources/csv/facturas.csv";
    private static final String DETALLESCSV = "src/main/resources/csv/facturas-productos.csv";
    private static final String PRODUCTOSCSV = "src/main/resources/csv/productos.csv";

    public static void main(String[] args) throws Exception {

        System.out.println();
        System.out.println("========================= Trabajo Práctico Integrador N°: 1 ======================");

        System.out.println();
        System.out.println("- 1 - creates the database and its driver...");
        instanciateDAOs();

        System.out.println();
        System.out.println("- 2 - create the database schema...");
        createTables();

        System.out.println();
        System.out.println("- 3 - Loading data from CSV files...");
        fillTables();

        System.out.println();
        System.out.println("- 4 - List all data in the tables...");
        listAllTables();

        // 5 - listar clientes ordenados por facturacion

        // 6 - listar el producto mas vendido

        System.out.println();
        System.out.println("- 7 - delete tables...");
        dropTables();

        System.out.println();
        System.out.println("- 8 - close the connection to the database...");
        factory.disconnect();

        System.out.println();
        System.out.println("============================================================================== FIN");

    }

    public static void instanciateDAOs() throws Exception {
        //clienteDAO = factory.getClienteDAO();
        //facturaDAO = factory.getfacturaDAO();
        //detalleDAO = factory.getdetalleDAO();
        //productoDAO = factory.getProductoDAO();
    }

    public static void createTables() throws Exception {
        //clienteDAO.createTable(factory.connect());
        //facturaDAO.createTable();
        //detalleDAO.createTable();
        //productoDAO.createTable();
    }

    public static void fillTables() throws Exception {

        ConvertCliente nuevoC = new ConvertCliente();
        System.out.println(nuevoC.convertCsv(CLIENTESCSV));

        ConvertFactura nuevaF = new ConvertFactura();
        System.out.println(nuevaF.convertCsv(FACTURASCSV));

        ConvertDetalle nuevoD = new ConvertDetalle();
        System.out.println(nuevoD.convertCsv(DETALLESCSV));

        ConvertProducto nuevoP = new ConvertProducto();
        System.out.println(nuevoP.convertCsv(PRODUCTOSCSV));

        //clienteDAO.loadCSVData(nuevo , factory.connect());

    }

    public static void listAllTables() throws Exception {
        //clienteDAO.listTable(factory.connect());
        //facturaDAO.listTable();
        //detalleDAO.listTable();
        //productoDAO.listTable();
    }

    public static void dropTables() throws Exception {
    //    clienteDAO.dropTable();
    //    facturaDAO.dropTable();
    //    detalleDAO.dropTable();
    //    productoDAO.dropTable();
    }

}

