package factory;

import dao.interfaces.ProductoDaoInterface;
import entities.Producto;

import java.sql.Connection;

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
                //instance = DBDerby.getInstance();
                System.out.println("Selection DERBY_JDBC");
                break;
            case 3:
                //instance = DBPostgres.getInstance();
                System.out.println("Selection POSTGRES_JDBC");
                break;
            default:
                System.out.println("Numero no valid.");
                instance = DBMySql.getInstance();
                break;
        }
        return instance;
    }
    public abstract Connection getConection();
    public abstract void closeConection();
    //public abstract FacturaDao getFacturaDao();
    public abstract ProductoDaoInterface<Producto> getProductoDao();
    //public abstract ClienteDao getClienteDao();
    //public abstract FacturaProductoDao getFacturaProductoDao();
}
