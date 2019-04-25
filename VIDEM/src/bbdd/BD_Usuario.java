package bbdd;

/**
 * @author Carolina
 */

import java.sql.*;

import modelos.Cliente;
import modelos.Empleado;
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
	public boolean borrar_Usuario(Usuario user,int num) {
		String cadenaSQL="DELETE FROM usuarios WHERE EMAIL='"+user.getEmail()+"'";
		String cadena2="";
		if(user instanceof Empleado) {
			cadena2="DELETE FROM empleados WHERE N_EMPLEADO ='"+num+"'";
			try {
				this.abrir();
				s = c.createStatement();
				s.executeQuery(cadena2);
				s.close();
				this.cerrar();
				return true;
			}catch(SQLException e) {
				this.cerrar();
				return false;
			}
		}
		if(user instanceof Cliente) {
			cadena2="DELETE FROM clientes WHERE N_SOCIO ='"+num+"'";
			try {
				this.abrir();
				s = c.createStatement();
				s.executeQuery(cadena2);
				s.close();
				this.cerrar();
				return true;
			}catch(SQLException e) {
				this.cerrar();
				return false;
			}
		}
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
	
	public String login(Usuario user){
		String cadena="SELECT ROL FROM usuarios WHERE EMAIL='" + user.getEmail() +"' AND PASSWORD='" + user.getPassword() +"'";
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next())							
				t= reg.getString(1);							
			s.close();
			this.cerrar();
			return t;
		}
		catch ( SQLException e){
	
			return null;
			
		}
	}
	
	public String loginEncargado(Usuario user){
		String cadena="SELECT PUESTO FROM empleados WHERE PUESTO IN ('ENCARGADO','ADMINISTRADOR')";
		try{
			String t="";
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next())							
				t= reg.getString(1);							
			s.close();
			this.cerrar();
			return t;
		}
		catch ( SQLException e){
	
			return null;
			
		}
	}
}
