package modelos;

import java.sql.Date;

public class Compra {
 private int numFactura;
 private int numCliente;
 private double precioT;
 private Date FHCompra;
 
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

public Date getFHCompra() {
	return FHCompra;
}
}
