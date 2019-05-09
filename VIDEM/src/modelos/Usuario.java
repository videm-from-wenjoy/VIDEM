package modelos;

/**
 * 
 * @author Carolina
 *
 */

public class Usuario {
	private String email, password,nombre,domicilio,dni,rol;
	private int telefono;
	
	public Usuario(String email, String password, String nombre, String domicilio, String dni, String rol,
			int telefono) {
		super();
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.dni = dni;
		this.rol = rol;
		this.telefono = telefono;
	}
	
		
	public Usuario(String emaill, String clavee){
		email=emaill;
		password=clavee;
	}
	
	public Usuario(String emailll){
		email=emailll;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", password=" + password + ", nombre=" + nombre + ", domicilio=" + domicilio
				+ ", dni=" + dni + ", rol=" + rol + ", telefono=" + telefono + "]";
	}
	
}
