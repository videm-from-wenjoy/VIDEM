package principal;
import java.util.*;

import bbdd.BD_Usuario;
import bbdd.BD_Videojuego;
import modelos.Usuario;
import modelos.Videojuego;

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
		System.out.println("Introduce tu contraseña");
		String clave=sc.nextLine();
		
		user = new Usuario(email,clave);				
		String opc=bbdd.login(user);
		
		if(opc=="EMPLEADO") {
			String opc2=bbdd.loginEncargado(user);
			if(opc2=="ADMINISTRADOR") {
				do {
					System.out.println("1º Ver solicitudes");
				}while(opc3!= 4);
			}
			if(opc2=="ENCARGADO") {
				do {
					System.out.println("1º Añadir videojuego");
					System.out.println("2º Borrar videojuego");
					System.out.println("3º ");
					opc3=sc.nextInt();
				}while(opc3!= 4);
			}
			else {
				
			}
		}
		else {
			
		}
	}

}
