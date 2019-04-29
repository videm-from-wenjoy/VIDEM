package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Compra;
import modelos.Linea;

public class BD_Compra extends BD_Conector {
  private Statement s;
  private ResultSet reg;
  
  public int añadirCompra(Compra co) {
	String ordenSQL= "INSERT INTO compras VALUES('" +
    co.getNumFactura() + "','" + co.getNumCliente() + "','" + 
	co.getPrecioT() + "','" + co.getFHCompra() + "')";
	
	try {
		this.abrir();
		s=(Statement) c.createStatement();
        int filas = s.executeUpdate(ordenSQL);
		s.close();
		this.cerrar();
		return filas;
	}catch(SQLException se) {
		System.err.println(se.getMessage());
	}
	  return -1;
  }
  
  public int añadirLinea(Linea li, Compra co) {
		String ordenSQL= "INSERT INTO lineas VALUES('" +
	    li.getNumFactura() + "','" + li.getCodProducto() + "','" + 
		li.getUnidades() + "','" + li.getPrecio() + "') WHERE  numFactura= '" +  co.getNumFactura() + "'";
		
		try {
			this.abrir();
			s=(Statement) c.createStatement();
	        int filas = s.executeUpdate(ordenSQL);
			s.close();
			this.cerrar();
			return filas;
		}catch(SQLException se) {
			System.err.println(se.getMessage());
		}
		  return -1;
	  }
}
