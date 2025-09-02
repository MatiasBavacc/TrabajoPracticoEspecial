package dao.interfaces;

public interface ProductoDaoInterface<Producto> {

    public void createDB();
    public void createTable();
    public void setTable(Producto producto);
    public void getTable();
    public void deleteTable();

}
