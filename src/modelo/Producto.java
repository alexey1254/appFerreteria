package modelo;


/*** @author Javier Criado, 15/05/2022:20:04:49  ***/
public class Producto  {
	private int codigo;
	private String nombre;
	private double precioCompra;
	private double precioVenta;
	private int stock;

	public Producto() {
		
	}
	public Producto(int codigo, String nombre, double precioCompra,
			double precioVenta, int stock) {
		this.codigo=codigo; this.nombre=nombre; this.precioCompra=precioCompra;
		this.precioVenta=precioVenta; this.stock=stock;
	}
	public Producto(Producto p) {
		this(p.getCodigo(),p.getNombre(),p.getPrecioCompra(),p.getPrecioVenta(),p.getStock());
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioCompra() {
		return precioCompra;
	}
	public String strPrecioCompra() {
		return String.format("%8.2f",this.getPrecioCompra()).replace(",",".");
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public String strPrecioVenta() {
		return String.format("%8.2f",this.getPrecioVenta()).replace(",",".");
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return String.format("%8d %-20s %8.2f %8.2f %8d",this.codigo,this.nombre,this.precioCompra,this.precioVenta,this.stock);
	}

}
