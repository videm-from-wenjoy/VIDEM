package modelos;
import java.time.LocalDate;
/**
 * @author Sergio Cruz
 * Clase Compra...
 *
 */

public class Compra {
	private int numFactura;
	private int numCliente;
	private double precioT;
	private LocalDate FHCompra;
  
	public Compra(int numFactura, int numCliente, double precioT, LocalDate fHCompra) {
		super();
		this.numFactura = numFactura;
		this.numCliente = numCliente;
		this.precioT = precioT;
		this.FHCompra = fHCompra;
	}

	public Compra(int numFactura) {
		super();
		this.numFactura = numFactura;
	}

	public int getNumFactura() {
		return numFactura;
	}

	public int getNumCliente() {
		return numCliente;
	}

	public double getPrecioT() {
		return precioT;
	}

	public LocalDate getFHCompra() {
		return FHCompra;
	}

	@Override
	public String toString() {
		return "Compra [numFactura=" + numFactura + ", numCliente=" + numCliente + ", precioT=" + precioT
				+ ", FHCompra=" + FHCompra + "]";
	}
}
