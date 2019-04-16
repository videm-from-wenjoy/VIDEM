package bbdd;

/**
 * @author carol
 */

import java.sql.*;
import modelos.Usuario;
public class BD_Usuario extends BD_Conector {
	private static Statement s;		
	private static ResultSet reg;
		
	public BD_Usuario(String bbdd){
		super (bbdd);
	}
	
	public boolean añadir_Usuario(Usuario user) {
		String cadenaSQL="INSERT INTO usuarios VALUES('"+user.getEmail()+"','"+user.getPassword()+"','"
						+user.getNombre()+"','"+user.getDomicilio()+"','"+user.getDni()+"','"+user.getRol()+"','"
						+user.getTelefono()+"')";
		try {
			this.abrir();
			 s=c.createStatement();
			 s.executeQuery(cadenaSQL);
			 s.close();
			 this.cerrar();
			 return true;
		}catch(SQLException e) {
			this.cerrar();
			return false;
		}
		
	}
	public boolean borrar_Usuario(String numUsuario) {
		String cadenaSQL="DELETE FROM usuarios WHERE numUsuario='"+numUsuario+"'";
		try {
			this.abrir();
			s = c.createStatement();
			s.executeQuery(cadenaSQL);
			s.close();
			this.cerrar();
			return true;
		}catch(SQLException e) {
			this.cerrar();
			return false;
		}
	}
}
