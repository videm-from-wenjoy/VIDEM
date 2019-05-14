package modelos;
/**
 * @author Sergio Cruz
 * Clase Linea
 */
public class Linea {
	private int numFactura;
	private String codProducto;
	private int unidades;
	private double precio;
  
	public Linea(int numFactura, String codProducto,int unidades) {
		super();
		this.numFactura = numFactura;
		this.codProducto = codProducto;
		this.unidades = unidades;
	}
  
	public Linea(int numFactura, String codProducto, int unidades, double precio) {
		super();
		this.numFactura = numFactura;
		this.codProducto = codProducto;
		this.unidades = unidades;
		this.precio = precio;
	}

	public int getNumFactura() {
		return numFactura;
	}

	public String getCodProducto() {
		return codProducto;
	}

	public int getUnidades() {
		return unidades;
	}

	public double getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "Linea [numFactura=" + numFactura + ", codProducto=" + codProducto + ", unidades=" + unidades
				+ ", precio=" + precio + "]";
	} 
}
