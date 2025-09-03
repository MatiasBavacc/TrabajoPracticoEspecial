package main;

import java.util.List;
import dao.interfaces.ClienteDaoInterface;
import dao.interfaces.FacturaDaoInterface;
import entities.Cliente;
import entities.Factura;
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
    private static ClienteDaoInterface<Cliente> clienteDAO;
    private static FacturaDaoInterface<Factura> facturaDAO;
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
        System.out.println("- 1 - Creates the database and its driver...");
        instanciateDAOs();

        System.out.println();
        System.out.println("- 2 - Create the database schema...");
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
        System.out.println("- 7 - Delete tables...");
        dropTables();

        System.out.println();
        System.out.println("- 8 - Close the connection to the database...");
        factory.disconnect();

        System.out.println();
        System.out.println("============================================================================== FIN");

    }

    public static void instanciateDAOs() throws Exception {
        clienteDAO = factory.getClienteDao();
        facturaDAO = factory.getFacturaDao();
        //detalleDAO = factory.getdetalleDAO();
        //productoDAO = factory.getProductoDAO();
    }

    public static void createTables() throws Exception {
        clienteDAO.createTable(factory.connect());
        facturaDAO.createTable(factory.connect());
        //detalleDAO.createTable();
        //productoDAO.createTable();
    }

    public static void fillTables() throws Exception {
    	
        ConvertCliente nuevoC = new ConvertCliente();
        List<Cliente> dataC = nuevoC.convertCsv(CLIENTESCSV);
        clienteDAO.loadCSVData(dataC , factory.connect());
       
        ConvertFactura nuevaF = new ConvertFactura();
        List<Factura> dataF = nuevaF.convertCsv(FACTURASCSV);
        facturaDAO.loadCSVData(dataF ,  factory.connect());

        //ConvertDetalle nuevoD = new ConvertDetalle();
        //System.out.println(nuevoD.convertCsv(DETALLESCSV));

        //ConvertProducto nuevoP = new ConvertProducto();
        //System.out.println(nuevoP.convertCsv(PRODUCTOSCSV));

    }

    public static void listAllTables() throws Exception {
        // clienteDAO.listTable(factory.connect());      	// <------ ya probado funciona bien
    	// facturaDAO.listTable(factory.connect());			// <------ ya probado funciona bien
        //detalleDAO.listTable();
        //productoDAO.listTable();
    }

    public static void dropTables() throws Exception {
        // clienteDAO.dropTable(factory.connect());      	// <------ ya probado funciona bien
    	// facturaDAO.dropTable(factory.connect());			// <------ ya probado funciona bien
    //    detalleDAO.dropTable();
    //    productoDAO.dropTable();
    }

}

