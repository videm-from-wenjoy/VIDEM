package modelos;

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
}
