package modelos;
/**
 * 
 * @author Carolina
 *
 */

public class Cliente extends Usuario{
	private int numCliente;

	public Cliente(String email, int numCliente) {
		super(email);
		this.numCliente = numCliente;
	}
	
	public Cliente(String email) {
		super(email);
	}

	public int getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(int numCliente) {
		this.numCliente = numCliente;
	}

	@Override
	public String toString() {
		return "Cliente [numCliente=" + numCliente + ", getEmail()=" + getEmail() + "]";
	}

	
	
}
