package dao.mysql;

import entities.Producto;
import java.sql.*;
import java.util.List;

public class ProductoDao implements dao.interfaces.ProductoDaoInterface<Producto> {

    @Override
    public void createTable(Connection conn) {
        String table = "CREATE TABLE IF NOT EXISTS producto (" +
                "idProducto INT, " +
                "nombre VARCHAR(250), " +
                "valor INT," +
                "PRIMARY KEY(idProducto)" +")";
        try {
            conn.prepareStatement(table).execute();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Error al intentar crear la tabla Producto" + e);
        }
    }

    @Override
    public void loadCSVData(List<Producto> data, Connection conn) {
        String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?,?,?)";
        try {
            PreparedStatement prepare = conn.prepareStatement(insert);
            //prepare.setInt(1, p.getIdProducto());
            //prepare.setString(2, p.getNombre());
            //prepare.setFloat(3, p.getValor());
            prepare.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Error en la inserción de datos en la tabla Producto.", e);
        }
    }

    @Override
    public void listTable(Connection conn) {
        String select = "SELECT * FROM producto";
        try  {
            PreparedStatement prepare = conn.prepareStatement(select);
            ResultSet result = prepare.executeQuery();
            while(result.next()){
                System.out.println(result.getInt(1) +
                        ", Nombre " + result.getString(2) +
                        ", Valor " + result.getInt(3) +
                        ".");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en la solicitud",e);
        }
    }

    @Override
    public void dropTable(Connection conn) {

    }
    
    @Override
    public void listarProductosMayorRecaudacion(Connection conn) throws Exception{
        System.out.println();
        System.out.println("	Buscando...");
        
    }


}
