package principal;
import java.util.*;
import bbdd.BD_Usuario;
import bbdd.BD_Videojuego;
import modelos.Usuario;
import modelos.Videojuego;
/**
 * 
 * @author Gonzalo Fern�ndez
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Usuario user;
		Videojuego vi;
		BD_Usuario bbdd=new BD_Usuario("videm");
		BD_Videojuego bbd=new BD_Videojuego("videm");
		int opc3;
		
		System.out.println("Introduce tu email");
		String email=sc.nextLine();
		System.out.println("Introduce tu contrase�a");
		String clave=sc.nextLine();
		
		user = new Usuario(email,clave);				
		String opc=bbdd.login(user);
		
		if(opc.equalsIgnoreCase("EMPLEADO") && opc.equalsIgnoreCase("CLIENTE") || opc==null) {
			System.out.println("Error, no estas en la base de datos");
			System.out.println("�Quieres registrarte en VIDEM? S/N");
			char respuesta=sc.nextLine().charAt(0);
			
			if(respuesta=='S') {
				System.out.println("Correo electronico");
				email=sc.nextLine();
				System.out.println("Clave personal:");
				clave=sc.nextLine();
				System.out.println("Nombre:");
				String nombre=sc.nextLine();
				System.out.println("Dni:");
				String dni=sc.nextLine();
				System.out.println("Domicilio:");
				String domicilio=sc.nextLine();
				System.out.println("Telefono:");
				int telefono=sc.nextInt();
				user = new Usuario(email,clave,nombre,domicilio,dni,"CLIENTE",telefono);
				if ( bbdd.a�adir_Usuario(user))
					System.out.println("Se ha dado de alta en VIDEM");
				else
					System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo m�s tarde.");
			}
			else {
				System.out.println("Vuelva pronto");
			}
		}
		if(opc.equalsIgnoreCase("EMPLEADO")) {
			String opc2=bbdd.loginEncargado(user);
			if(opc2=="ADMINISTRADOR") {
				do {
					System.out.println("1� Ver solicitudes");
					System.out.println("2� ");
					opc3=sc.nextInt();
				}while(opc3!= 4);
			}
			if(opc2.equalsIgnoreCase("ENCARGADO")) {
				do {
					System.out.println("1� A�adir videojuego");
					System.out.println("2� Borrar videojuego");
					System.out.println("3� Recuento de ventas");
					opc3=sc.nextInt();
				}while(opc3!= 4);
			}
			else {
				
			}
		}
		if(opc.equalsIgnoreCase("CLIENTE")) {
			
		}
	}

}
