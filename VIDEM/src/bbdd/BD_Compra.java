package bbdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import modelos.Cliente;
import modelos.Compra;
import modelos.Linea;
import modelos.Venta;

public class BD_Compra extends BD_Conector {
	private Statement s;
	private ResultSet reg;
	
	public BD_Compra(String bbdd){
		super (bbdd);
	}
  /**
   * @author Sergio Cruz
   * @param co
   * @return
   */
  public int añadirCompra(Compra co) {
	String ordenSQL= "INSERT INTO compras VALUES('" +
    co.getNumFactura() + "','" + co.getNumCliente() + "',' SELECT SUM(PRECIO) FROM LINEAS WHERE N_FACTURA ='"+co.getNumFactura()+",'" + co.getFHCompra() + "')";
	
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
  /**
   * @author Sergio Cruz
   * @param li
   * @param co
   * @return
   */
  public int añadirLinea(Linea li, Compra co) {
		String ordenSQL= "INSERT INTO lineas VALUES('" +li.getNumFactura() + "','" + li.getCodProducto() + "','" + li.getUnidades() + "','" 
		+ "'(SELECT PRECIO*'"+li.getUnidades()+"' FROM VIDEOJUEGOS WHERE COD_PRODUCTO ='" +li.getCodProducto()+ "') WHERE  numFactura= '" +  co.getNumFactura() + "'";
		
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
  /**
   * @author Sergio Cruz
   * @return
   */ 
  public Vector<Compra>  compras(){
		Vector <Compra> ca=new Vector<Compra>();
		String cadenaC="SELECT * FROM COMPRAS";
		try{	
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaC);
			while ( reg.next()){						
				 ca.add(new Compra(reg.getInt("N_FACTURA"),reg.getInt("N_SOCIO"),reg.getDouble("PRECIO_TOTAL"),(reg.getDate("FH_COMPRA")).toLocalDate()));
			}	
			s.close();
			this.cerrar();
			return ca;
		}
		catch ( SQLException e){
			return null;		
		}				
  }
  
  public Vector<Venta>  ventas(){
		Vector <Venta> ca=new Vector<Venta>();
		String cadenaC="SELECT V.COD_PRODUCTO PRODUCTO, TITULO, V.UNIDADES STOCK,SUM(L.UNIDADES) VENDIDAS, V.PRECIO*SUM(L.UNIDADES) RECAUDACION "
				+ "FROM LINEAS L,VIDEOJUEGOS V "
				+ "WHERE V.COD_PRODUCTO = L.COD_PRODUCTO "
				+ "GROUP BY V.COD_PRODUCTO,TITULO";
		try{	
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaC);
			while ( reg.next()){						
				 ca.add(new Venta(reg.getString("PRODUCTO"),reg.getString("TITULO"),reg.getInt("STOCK"),reg.getInt("VENDIDAS"),reg.getDouble("RECAUDACION")));
			}	
			s.close();
			this.cerrar();
			return ca;
		}
		catch ( SQLException e){
			return null;		
		}				
}
  /**
   * Método donde el usuario podrá listar sus comprar para verlas por pantalla.
   * @author Gonzalo Fernandez
   * @return
   */
  public Vector<Compra>  comprasPropias(Cliente cl){
		Vector <Compra> ca=new Vector<Compra>();
		String cadenaC="SELECT N_FACTURA, C.N_SOCIO CLIENTE, PRECIO_TOTAL, FH_COMPRA "
				+ "FROM COMPRAS C, CLIENTES E "
				+ "WHERE C.N_SOCIO = E.N_SOCIO "
				+ "AND EMAIL ='"+cl.getEmail()+"'";
		try{	
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaC);
			while ( reg.next()){						
				 ca.add(new Compra(reg.getInt("N_FACTURA"),reg.getInt("CLIENTE"),reg.getDouble("PRECIO_TOTAL"),reg.getDate("FH_COMPRA").toLocalDate()));
			}	
			s.close();
			this.cerrar();
			return ca;
		}
		catch ( SQLException e){
			return null;		
		}				
  }
  /**
   * Método donde el usuario podrá listar las líneas de sus compras para verlas por pantalla.
   * @author Gonzalo Fernandez
   * @return
   */
  public Vector<Linea>  lineas(Compra co){
		Vector <Linea> la=new Vector<Linea>();
		String cadenaL="SELECT * FROM LINEAS WHERE N_FACTURA = '"+co.getNumFactura()+"'";
		try{	
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaL);
			while ( reg.next()){						
				la.add(new Linea(reg.getInt("N_FACTURA"),reg.getString("COD_PRODUCTO"),reg.getInt("UNIDADES"),reg.getDouble("PRECIO")));
			}	
			s.close();
			this.cerrar();
			return la;
		}
		catch ( SQLException e){
			return null;		
		}				
  }
  /**
   * @author Sergio Cruz
   * @param co
   * @return
   */
  public int updateCompra(Compra co){
	  String cadenaSQL="UPDATE compras SET PRECIO_TOTAL=SELECT SUM(PRECIO) FROM lineas WHERE N_FACTURA='"+co.getNumFactura()+"'";
	  
	  try {
		  this.abrir();
		  s = c.createStatement();
		  int filas = s.executeUpdate(cadenaSQL);
		  s.close();
		  this.cerrar();
		  return filas;		  
	  }catch(SQLException e) {
		  this.cerrar();
		  return -1;
	  }
	  
  }
  
}
