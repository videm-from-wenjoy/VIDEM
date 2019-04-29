package bbdd;

/**
 * @author Carolina
 */

import java.sql.*;

import modelos.Cliente;
import modelos.Empleado;
import modelos.Usuario;
import java.util.*;

public class BD_Usuario extends BD_Conector {
	private static Statement s;		
	private static ResultSet reg;
		
	public BD_Usuario(String bbdd){
		super (bbdd);
	}
	/**
	 * 
	 * @param 
	 * @return Devuelve verdadero(true) si se ha podido a�adir un nuevo usuario y falso(false)
	 * si no se puede a�adir un usuario
	 */
	public boolean a�adir_Usuario(Usuario user) {
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
	/**
	 * 
	 * @param user
	 * @param num
	 * @return 
	 */
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
	/**
	 * 
	 * @param user
	 * @return
	 */
	
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
	/**
	 * 
	 * @param user
	 * @return
	 */
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
	/**
	 * 
	 * @param user
	 * @param campo
	 * @param contenido
	 * @return
	 */
	public int editarUsuario(Usuario user, int campo, String contenido) {
		String cadenaSQL="";
		if (campo==1) 
			cadenaSQL="UPDATE usuarios SET EMAIL='"+contenido+"' WHERE DNI='"+user.getDni()+"'";
		if (campo==2)
			cadenaSQL="UPDATE usuarios SET PASSWORD='"+contenido+"' WHERE DNI='"+user.getDni()+"'";
		if(campo==3)
			cadenaSQL="UPDATE usuarios SET ROL='"+contenido+"' WHERE DNI='"+user.getDni()+"'";
		if(campo==4)
			cadenaSQL="UPDATE usuarios SET NOMBRE='"+contenido+"' WHERE DNI='"+user.getDni()+"'";
		if(campo==5)
			cadenaSQL="UPDATE usuarios SET DIRECCION='"+contenido+"' WHERE DNI='"+user.getDni()+"'";
		if(campo==6)
			cadenaSQL="UPDATE usuarios SET TELEFONO='"+contenido+"' WHERE DNI='"+user.getDni()+"'";
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
	/**
	 * Muestra una lista completa de todos los usuarios.
	 * @return
	 */
	public Vector<Usuario> listarUsuarios(){
		String cadenaSQL="SELECT * FROM usuarios";
		Vector<Usuario> users = new Vector<Usuario>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while(reg.next()) {
				users.add(new Usuario(reg.getString("EMAIL"),reg.getString("PASSWORD"),reg.getString("ROL"),reg.getString("NOMBRE"),reg.getString("DNI"),reg.getString("DIRECCION"),reg.getInt("TELEFONO")));
			}
			s.close();
			this.cerrar();
			return users;
		}catch(SQLException e) {
			this.cerrar();
			return null;
		}
	}
	
	
}
