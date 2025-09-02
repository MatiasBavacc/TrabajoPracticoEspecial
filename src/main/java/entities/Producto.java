package entities;

public class Producto {
    private int id;
    private String nombre;
    private int valor;

    public Producto(int id, String nombre, int valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValor() {
        return valor;
    }
}
