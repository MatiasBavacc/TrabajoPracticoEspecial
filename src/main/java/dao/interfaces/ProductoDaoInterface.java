package dao.interfaces;

import java.sql.Connection;
import java.util.List;

public interface ProductoDaoInterface<Producto> {

    // table management

    void createTable(Connection conn) throws Exception;

    void loadCSVData(List<Producto> data, Connection conn) throws Exception;

    void listTable(Connection conn) throws Exception;

    void dropTable(Connection conn) throws Exception;
    
    void listarProductosMayorRecaudacion(Connection conn) throws Exception;

}
