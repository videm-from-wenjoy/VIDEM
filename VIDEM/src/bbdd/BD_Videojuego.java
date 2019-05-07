package bbdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.*;
import modelos.Videojuego;
/**
 * @author Gonzalo Fern�ndez
 */
public class BD_Videojuego extends BD_Conector {
	private static Statement s;		
	private static ResultSet reg;
		
	public BD_Videojuego(String bbdd){
		super (bbdd);
	}
	/**
	 * M�todo que inserta en un registro de la base de datos, los campos que hemos pasado por teclado para crear un nuevo videojuego.
	 * @param vi
	 * @return
	 */
	public boolean a�adirJuego(Videojuego vi){
		String cadena="INSERT INTO videojuegos VALUES('" + vi.getCodProducto() + "','" + vi.getTitulo()+"','"+ vi.getLanzamiento() +"','"
		+ vi.getPlataforma()+"','"+ vi.getGenero()+"','"+ vi.getPegi()+"','"+ vi.getUnidades()+"','"+ vi.getPrecio()+"')"; 	
		
		try{
			this.abrir();
			s=c.createStatement();
			s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return true;
		}
		catch ( SQLException e){
			this.cerrar();
			return false;
		}	
	}
	/**
	 * M�todo por el que buscamos un videojuego por su c�digo de producto para borrarlo de la base de datos.
	 * @param vi
	 * @return
	 */
	public int borrarVideojuego(Videojuego vi){
		String cadena="DELETE FROM videojuegos WHERE COD_PRODUCTO='" +  vi.getCodProducto() + "'";	
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadena);	
		s.close();
		this.cerrar();
		return filas;
		
		}
		catch ( SQLException e){
			this.cerrar();
			return -1;
		}
	}
	/**
	 * M�todo que lista todos los registros de la tabla videojuegos de la base de datos y los muestra por pantalla.
	 * @return v o null, devuelva las filas o nada si la tabla esta vac�a.
	 */
	public Vector<Videojuego>  listarVideojuegos(){
		Vector <Videojuego> v=new Vector<Videojuego>();
		String cadena="SELECT * FROM videojuegos ";
		try{
			
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while ( reg.next()){						
				 v.add(new Videojuego(reg.getString("COD_PRODUCTO"),reg.getString("TITULO"),(reg.getDate("LANZAMIENTO")).toLocalDate(),reg.getString("PLATAFORMA"),reg.getString("GENERO"),reg.getString("PEGI"),reg.getInt("UNIDADES"),reg.getDouble("PRECIO")));
			}
			
			s.close();
			this.cerrar();
			return v;
		}
		catch ( SQLException e){
	
			return null;
			
		}				
	
	
	}
	/**
	 * M�todo que devuelve un producto concreto al buscarlo por 2 combinaciones de campos especificos.
	 * @param campo, Se pasa un n�mero que indica el campo de la tabla que ser� el criterio de busqueda.
	 * @param contenido, Se pasa una cadena de texto que ser� el c�digo de producto o el titulo en funci�n del campo.
	 * @return
	 */
	public Vector<Videojuego> buscarVideojuego(int campo, String contenido) {
		Vector<Videojuego> v=new Vector<Videojuego>();
		Scanner sc=new Scanner(System.in);
		String cadenaSQL="";		
		if(campo==1)
			cadenaSQL="SELECT * FROM videojuegos WHERE COD_PRODUCTO='"+contenido+"'";
		if(campo==2)
			System.out.println("Anota la plataforma del juego: ");
			String plataforma=sc.nextLine();
			cadenaSQL="SELECT * FROM videojuegos WHERE TITULO='"+contenido+"' AND PLATAFORMA='"+plataforma+"'";
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while(reg.next()) {
				v.add(new Videojuego(reg.getString("COD_PRODUCTO"),reg.getString("TITULO"),(reg.getDate("LANZAMIENTO")).toLocalDate(),reg.getString("PLATAFORMA"),reg.getString("GENERO"),reg.getString("PEGI"),reg.getInt("UNIDADES"),reg.getDouble("PRECIO")));		
			}
			return v;
		}catch(SQLException e) {
			this.cerrar();
			return null;
		}
	}
}