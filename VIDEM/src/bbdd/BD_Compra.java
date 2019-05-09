package bbdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import modelos.Compra;
import modelos.Linea;

public class BD_Compra extends BD_Conector {
	private Statement s;
	private ResultSet reg;
	
	public BD_Compra(String bbdd){
		super (bbdd);
	}
  
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
  
  public Vector<Linea>  listarComprasPorUsuario(){
		Vector <Linea> v=new Vector<Linea>();
		String cadena="SELECT * FROM videojuegos WHERE COD_PRODUCTO IN (SELECT COD_PRODUCTO FROM lineas)";
		try{	
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while ( reg.next()){						
				 v.add(new Linea(reg.getInt("N_FACTURA"),reg.getString("COD_PRODUCTO"),reg.getInt("UNIDADES"),reg.getDouble("PRECIO")));
			}	
			s.close();
			this.cerrar();
			return v;
		}
		catch ( SQLException e){
			return null;		
		}				
  }
  
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
  /**
   * Método donde el usuario podrá listar sus comprar para verlas por pantalla.
   * @return
   */
  public Vector<Compra>  comprasPropias(){
		Vector <Compra> ca=new Vector<Compra>();
		String cadenaC="SELECT * FROM COMPRAS WHERE N_Socio ='"f"'";
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
  /**
   * Método donde el usuario podrá listar las líneas de sus compras para verlas por pantalla.
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
