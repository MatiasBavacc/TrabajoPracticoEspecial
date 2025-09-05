package factory;

import java.sql.Connection;
import dao.interfaces.ClienteDaoInterface;
import dao.interfaces.DetalleDaoInterface;
import dao.interfaces.FacturaDaoInterface;
import dao.interfaces.ProductoDaoInterface;
import entities.Cliente;
import entities.Detalle;
import entities.Factura;
import entities.Producto;

public abstract class BaseDeDatosFactory {

    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public static final int POSTGRES_JDBC = 3;

    public static BaseDeDatosFactory getFactory(int factory){
        BaseDeDatosFactory instance = null;
        switch (factory){
            case 1:
                instance = DBMySql.getInstance();
                break;
            case 2:
                instance = DERBYFactory.getInstance();
                break;
            default:
                System.out.println("Numero no valid.");
                instance = DBMySql.getInstance();
                break;
        }
        return instance;
    }

    public abstract Connection connect() throws Exception;

    public abstract void disconnect() throws Exception;

    public abstract ClienteDaoInterface<Cliente> getClienteDao();
    
    public abstract FacturaDaoInterface<Factura> getFacturaDao();

    public abstract DetalleDaoInterface<Detalle> getDetalleDao();

    public abstract ProductoDaoInterface<Producto> getProductoDao();

}
