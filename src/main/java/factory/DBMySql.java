package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import dao.interfaces.ClienteDaoInterface;
import dao.interfaces.DetalleDaoInterface;
import dao.interfaces.FacturaDaoInterface;
import dao.interfaces.ProductoDaoInterface;
import dao.mysql.ClienteDao;
import dao.mysql.DetalleDao;
import dao.mysql.FacturaDao;
import dao.mysql.ProductoDao;
import entities.Cliente;
import entities.Detalle;
import entities.Factura;
import entities.Producto;

public class DBMySql extends BaseDeDatosFactory {

    private static DBMySql instance = null;
    private String uri = "jdbc:mysql://localhost:3306/Entregable1";
    private static Connection conn;

    private DBMySql() {
    }

    public static synchronized DBMySql getInstance() {
        if (instance == null) {
            instance = new DBMySql();
        }
        return instance;
    }

    //Joya son unos capos!! gracias sisis de 10000!
    @Override
    public Connection connect() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(uri, "root", "");
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    @Override
    public void disconnect() {
        try {
            if (conn != null) {
                conn.rollback();
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error cerrando la conexión", e);
        }
    }

    @Override
    public ClienteDaoInterface<Cliente> getClienteDao() {
        return new ClienteDao();
    }

    @Override
    public FacturaDaoInterface<Factura> getFacturaDao() {
        return new FacturaDao();
    }
    
    @Override
    public DetalleDaoInterface<Detalle> getDetalleDao() { return new DetalleDao(); }
    
    @Override
    public ProductoDaoInterface<Producto> getProductoDao() {
        return new ProductoDao();
    }
    
}

