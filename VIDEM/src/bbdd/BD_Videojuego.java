package bbdd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import modelos.Videojuego;

public class BD_Videojuego extends BD_Conector {
	private static Statement s;		
	private static ResultSet reg;
		
	public BD_Videojuego(String bbdd){
		super (bbdd);
	}
	
	public boolean añadirJuego(Videojuego vi){
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
}