package modelo;

/**
 * @author Alejandro
 */
public class Producto {
    private int codigo;
    private String nombre;
    private double precioCompra;
    private double precioVenta;
    private int stock;

    public Producto() {
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Constructor con parametros
     * 
     * @param codigo
     * @param nombre
     * @param precioCompra
     * @param precioVenta
     * @param stock
     */
    public Producto(int codigo, String nombre, double precioCompra, double precioVenta, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    /**
     * Constructor donde le pasas un objeto Producto
     * 
     * @param producto
     */
    public Producto(Producto producto) {
        this.codigo = producto.codigo;
        this.nombre = producto.nombre;
        this.precioCompra = producto.precioCompra;
        this.precioVenta = producto.precioVenta;
        this.stock = producto.stock;
    }

    @Override
    public String toString() {
        return String.format("[\nCodigo: %s\nNombre: %s\nPrecio de Compra: %s\nPrecio de Venta: %s\nStock: %s\n]",
                codigo,
                nombre, precioCompra, precioVenta, stock);
    }

    @Override
    public boolean equals(Object obj) {
        return ((Producto) obj).codigo == this.codigo && ((Producto) obj).nombre == this.nombre
                && ((Producto) obj).precioCompra == this.precioCompra
                && ((Producto) obj).precioVenta == this.precioVenta && ((Producto) obj).stock == this.stock;
    }
}
