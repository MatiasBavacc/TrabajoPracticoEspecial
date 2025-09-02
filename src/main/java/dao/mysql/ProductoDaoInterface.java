package dao.mysql;

import entities.Producto;

import java.sql.*;

public class ProductoDaoInterface implements dao.interfaces.ProductoDaoInterface<Producto> {
    private Connection conn;

    public ProductoDaoInterface(Connection conn){this.conn = conn;}

    @Override
    public void createDB() {}

    @Override
    public void createTable() {
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
    public void setTable(Producto p) {
        String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?,?,?)";
        try {
            PreparedStatement prepare = conn.prepareStatement(insert);
            prepare.setInt(1, p.getId());
            prepare.setString(2, p.getNombre());
            prepare.setInt(3, p.getValor());
            prepare.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Error en la inserción de datos en la tabla Producto.", e);
        }
    }

    @Override
    public void getTable() {
        String select = "SELECT * FROM producto";
        try  {
            PreparedStatement prepare = this.conn.prepareStatement(select);
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
    public void deleteTable() {

    }

    public void productomas() {
        System.out.println("el algodon es el mas vendido");
    }

}
