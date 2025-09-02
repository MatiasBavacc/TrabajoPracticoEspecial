package factory;

import dao.mysql.ProductoDaoInterface;
import entities.Producto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMySql extends BaseDeDatosFactory {

    private static DBMySql instance = null;
    private String uri = "jdbc:mysql://localhost:3306/myDB";
    private static Connection conn;

    private DBMySql (){}

    public static DBMySql getInstance(){
        if(instance == null){
            //instance = new DBMySql();
            synchronized (DBMySql.class) {
                if (instance == null) {
                    instance = new DBMySql();
                }
            }
        }
        return instance;
    }
        //Joya son unos capos!! gracias sisis de 10000!
    @Override
    public Connection getConection() {
        if(conn == null){
            try {
                conn = DriverManager.getConnection(uri, "root", "password");
                conn.setAutoCommit(false);
                //this.conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }

    @Override
    public void closeConection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error cerrando la conexión", e);
        }
    }

    @Override
    public ProductoDaoInterface getProductoDao() {
        return new ProductoDaoInterface(this.getConection());
    }


}
