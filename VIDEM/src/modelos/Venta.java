package modelos;

public class Venta {
	private String codProducto;
	private String titulo;
	private int stock;
	private int vendido;
	private double recaudacion;
	
	public Venta(String codProducto, String titulo, int stock, int vendido, double recaudacion) {
		super();
		this.codProducto = codProducto;
		this.titulo = titulo;
		this.stock = stock;
		this.vendido = vendido;
		this.recaudacion = recaudacion;
	}

	@Override
	public String toString() {
		return "Venta [codProducto=" + codProducto + ", titulo=" + titulo + ", stock=" + stock + ", vendido=" + vendido
				+ ", recaudacion=" + recaudacion + "]";
	}
}
