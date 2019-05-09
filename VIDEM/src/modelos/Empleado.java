package modelos;
/**
 * 
 * @author Carolina
 *
 */

public class Empleado extends Usuario{
	private int numEmpleado;
	private String puesto;

	public Empleado(String email, String password, String nombre, String domicilio, String dni, String rol,
			int telefono, int numEmpleado, String puesto) {
		super(email, password, nombre, domicilio, dni, rol, telefono);
		this.numEmpleado = numEmpleado;
		this.puesto = puesto;
	}
	
	public Empleado(String email) {
		super(email);
		// TODO Auto-generated constructor stub
	}



	public Empleado(String emaill, String clavee) {
		super(emaill, clavee);
		// TODO Auto-generated constructor stub
	}



	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	
	
}
