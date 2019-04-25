package modelos;
/**
 * 
 * @author Carolina
 *
 */

public class Empleado extends Usuario{
	private int numEmpleado;

	public Empleado(String email, String password, String nombre, String domicilio, String dni, String rol,
			int telefono, int numCliente) {
		super(email, password, nombre, domicilio, dni, rol, telefono);
		this.numEmpleado = numCliente;
	}

	public int getNumEmpleado() {
		return numEmpleado;
	}

	public void setNumEmpleado(int numEmpleado) {
		this.numEmpleado = numEmpleado;
	}
	
	
}
