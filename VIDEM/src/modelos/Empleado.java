package modelos;
/**
 * 
 * @author Carolina
 *
 */

public class Empleado extends Usuario{
	private int numEmpleado;
	private String puesto;

	public Empleado(String email, int numEmpleado, String puesto) {
		super(email);
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

	public String getPuesto() {
		return puesto;
	}

	@Override
	public String toString() {
		return "Empleado [numEmpleado=" + numEmpleado + ", puesto=" + puesto + "]";
	}
	
}
