package bbdd;
/**
 * @author Carolina Buenaño
 */
import java.sql.*;
import modelos.Cliente;
import modelos.Empleado;
import modelos.Usuario;
import java.util.*;

public class BD_Usuario extends BD_Conector {
	private static Statement s;
	private static ResultSet reg;

	public BD_Usuario(String bbdd) {
		super(bbdd);
	}

	/**
	 * 
	 * @param Se anotan los datos de un usuario.
	 * @return Devuelve true si se ha podido añadir un usuario y false si hubo algún error. 
	 */
	public boolean añadir_Usuario(Usuario user) {
		String cadenaSQL = "INSERT INTO usuarios VALUES('" + user.getEmail() + "','" + user.getPassword() + "','"
				+ user.getRol() + "','" + user.getNombre() + "','" + user.getDni() + "','" + user.getDomicilio() + "','"
				+ user.getTelefono() + "')";
		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return true;
		} catch (SQLException e) {
			this.cerrar();
			return false;
		}

	}
	
	public boolean añadir_Empleado(Empleado emp) {
		String cadenaSQL = "INSERT INTO empleados VALUES('" + emp.getEmail() + "','" + emp.getNumEmpleado() + "','"
				+ emp.getPuesto() + "')";
		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return true;
		} catch (SQLException e) {
			this.cerrar();
			return false;
		}
	}
	/**
	 * @author admin
	 * @param cl
	 * @return
	 */
	public boolean añadir_Cliente(Cliente cl) {
		String cadenaSQL = "INSERT INTO clientes VALUES('" + cl.getNumCliente() + "','" + cl.getEmail() + "')";
		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return true;
		} catch (SQLException e) {
			this.cerrar();
			return false;
		}
	}

	/**
	 * @author Carolina Buenaño
	 * @param Se anota el e-mail
	 * @return Devuelve true si se ha podido borrar y false si hay hubo error. 
	 */
	public boolean borrar_Usuario(Usuario user) {
		String cadenaSQL = "DELETE FROM usuarios WHERE EMAIL='" + user.getEmail() + "'";
		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return true;
		} catch (SQLException e) {
			this.cerrar();
			return false;
		}
	}
	
	/**
	 * @author Carolina Buenaño y Gonzalo Fernández
	 * @param user
	 * @return
	 */
	public boolean borrar_Cliente(Usuario user) {
		String cadenaSQL = "DELETE FROM clientes WHERE EMAIL ='" + user.getEmail() + "'";
		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return true;
		} catch (SQLException e) {
			this.cerrar();
			return false;
		}
	}
	
	/**
	 * @author Carolina Buenaño y Gonzalo Fernández
	 * @param user
	 * @return
	 */
	public boolean borrar_Empleado(Usuario user) {
		String cadenaSQL = "DELETE FROM empleados WHERE EMAIL ='" + user.getEmail() + "'";
		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return true;
		} catch (SQLException e) {
			this.cerrar();
			return false;
		}
	}

	/**	
	 * @author Gonzalo Fernandez
	 * @param Se anota el email del usuario y su contraseña- 
	 * @return Devuelve null si hubo un error, un valor vacío si no encuentra el rol y si lo encuentra devuelve el rol. 
	 */
	public String login(Usuario user) {
		String cadena = "SELECT ROL FROM usuarios WHERE EMAIL='" + user.getEmail() + "' AND PASSWORD='"
				+ user.getPassword() + "'";
		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next())
				t = reg.getString(1);
			s.close();
			this.cerrar();
			return t;
		} catch (SQLException e) {

			return null;

		}
	}
	/**
	 * @author Gonzalo Fernandez
	 * @param 
	 * @return Devuelve null si hubo un error, un valor vacío si no encuentra el puesto y si lo encuentra devuelve el puesto. 
	 */
	public String loginEncargado(Usuario user) {
		String cadena = "SELECT PUESTO FROM empleados WHERE PUESTO IN ('ENCARGADO','ADMINISTRADOR') AND EMAIL='"+user.getEmail()+"'";
		try {
			String t = "";
			//
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			if (reg.next())
				t = reg.getString(1);
			s.close();
			this.cerrar();
			return t;
		} catch (SQLException e) {

			return null;

		}
	}
	/**
	 * @author Carolina Buenaño
	 * @param Se anota el DNI del usuario
	 * @param Se anota campo para saber la opción que queremos editar
	 * @param El contenido es la información que va a cambiar
	 * @return Devuelve -1 si hubo algún error, 0 si no modificó nada y 1 si pudo modificar la información
	 */
	public int editarUsuario(Usuario user, int campo, String contenido) {
		String cadenaSQL = "";
		if (campo == 1)
			cadenaSQL = "UPDATE usuarios SET EMAIL='" + contenido + "' WHERE DNI='" + user.getDni() + "'";
		if (campo == 2)
			cadenaSQL = "UPDATE usuarios SET PASSWORD='" + contenido + "' WHERE DNI='" + user.getDni() + "'";
		if (campo == 3)
			cadenaSQL = "UPDATE usuarios SET ROL='" + contenido + "' WHERE DNI='" + user.getDni() + "'";
		if (campo == 4)
			cadenaSQL = "UPDATE usuarios SET NOMBRE='" + contenido + "' WHERE DNI='" + user.getDni() + "'";
		if (campo == 5)
			cadenaSQL = "UPDATE usuarios SET DIRECCION='" + contenido + "' WHERE DNI='" + user.getDni() + "'";
		if (campo == 6)
			cadenaSQL = "UPDATE usuarios SET TELEFONO='" + contenido + "' WHERE DNI='" + user.getDni() + "'";
		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return filas;
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}

	/**
	 * Muestra una lista completa de todos los usuarios.
	 * 
	 */
	public Vector<Usuario> listarUsuarios() {
		String cadenaSQL = "SELECT * FROM usuarios";
		Vector<Usuario> users = new Vector<Usuario>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				users.add(new Usuario(reg.getString("EMAIL"), reg.getString("PASSWORD"), reg.getString("ROL"),
						reg.getString("NOMBRE"), reg.getString("DNI"), reg.getString("DIRECCION"),
						reg.getInt("TELEFONO")));
			}
			s.close();
			this.cerrar();
			return users;
		} catch (SQLException e) {
			this.cerrar();
			return null;
		}
	}
	
	public Vector<Empleado> listarEmpleados() {
		String cadenaSQL = "SELECT * FROM empleados";
		Vector<Empleado> emp = new Vector<Empleado>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				emp.add(new Empleado(reg.getString("EMAIL"), reg.getInt("N_EMPLEADO"), reg.getString("PUESTO")));
			}
			s.close();
			this.cerrar();
			return emp;
		} catch (SQLException e) {
			this.cerrar();
			return null;
		}
	}
	
	public Vector<Cliente> listarClientes() {
		String cadenaSQL = "SELECT * FROM clientes";
		Vector<Cliente> cli = new Vector<Cliente>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				cli.add(new Cliente(reg.getString("EMAIL"), reg.getInt("N_SOCIO")));
			}
			s.close();
			this.cerrar();
			return cli;
		} catch (SQLException e) {
			this.cerrar();
			return null;
		}
	}	

	/**
	 * @author Gonzalo Fernandez
	 * @param user
	 * @return t, que es el maximo número de empleado de tabla para que se almacene en una variable a la que se sumara 1 para ser el número de empleado del próximo empleado.
	 */
	public int asignarNumEmpleado(Usuario user){
		String cadena="SELECT MAX(N_EMPLEADO) FROM empleados";
		try{
			int t=0;
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next())							
				t= reg.getInt(1);							
			s.close();
			this.cerrar();
			return t;
		}
		catch ( SQLException e) {
			return -1;
		}
	}
	
	/**
	 * @author Gonzalo Fernandez
	 * @param user
	 * @return t, que es el maximo número de cliente de tabla para que se almacene en una variable a la que se sumara 1 para ser el número de cliente del próximo cliente.
	 */
	public int asignarNumCliente(Usuario user){
		String cadena="SELECT MAX(N_SOCIO) FROM clientes";
		try{
			int t=0;
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			if ( reg.next())							
				t= reg.getInt(1);							
			s.close();
			this.cerrar();
			return t;
		}
		catch ( SQLException e) {
			return -1;
		}
	}
	
	/**
	 * @author Carolina Buenaño
	 * @param user
	 * @return
	 */
	public String validarDNI(Usuario user) {
		String cadenaSQL = "SELECT DNI FROM usuarios WHERE DNI='" + user.getDni() + "'";
		try {
			String t = "";
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			if (reg.next())
				t = reg.getString("DNI");
			s.close();
			this.cerrar();
			return t;
		} catch (SQLException e) {
			this.cerrar();
			return null;
		}
	}
	
	/**
	 * @author Carolina Buenaño
	 * @param user
	 * @return
	 */
	public int validarTELEFONO(Usuario user) {
		String cadenaSQL = "SELECT TELEFONO FROM usuarios WHERE TELEFONO='" + user.getTelefono() + "'";
		try {
			int t = 0;
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			if (reg.next())
				t = reg.getInt("TELEFONO");
			s.close();
			this.cerrar();
			return t;
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}	

}
