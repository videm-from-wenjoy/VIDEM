package modelos;

public class Cliente extends Usuario{
	private int numCliente;

	public Cliente(String email, String password, String nombre, String domicilio, String dni, String rol, int telefono,
			int numCliente) {
		super(email, password, nombre, domicilio, dni, rol, telefono);
		this.numCliente = numCliente;
	}

	public int getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(int numCliente) {
		this.numCliente = numCliente;
	}
	
	
}
