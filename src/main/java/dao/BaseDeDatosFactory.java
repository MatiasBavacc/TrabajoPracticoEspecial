package dao;

public abstract class BaseDeDatosFactory {
    public static final int MYSQL_JDBC = 1;
    public static final int DERBY_JDBC = 2;
    public static final int POSTGRES_JDBC = 3;

    public BaseDeDatosFactory(){

    }
    protected abstract BaseDeDatos getConection();
}
