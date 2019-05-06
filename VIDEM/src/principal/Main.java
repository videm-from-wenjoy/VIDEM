package principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import bbdd.BD_Usuario;
import bbdd.BD_Videojuego;
import modelos.Empleado;
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
		String opc;
		char opcIni;
		int opc3;
		LocalDate lanzamiento=null;
		
		System.out.println("�Quieres registrarte en VIDEM? S/N");
		opcIni=sc.nextLine().charAt(0);
		if(opcIni=='S' || opcIni=='s') {
			System.out.println("Correo electronico");
			String email=sc.nextLine();
			System.out.println("Clave personal:");
			String clave=sc.nextLine();
			System.out.println("Nombre:");
			String nombre=sc.nextLine();
			System.out.println("Dni:");
			String dni=sc.nextLine();
			System.out.println("Domicilio:");
			String domicilio=sc.nextLine();
			System.out.println("Telefono:");
			int telefono=sc.nextInt();
			user = new Usuario(email,clave,nombre,domicilio,dni,"CLIENTE",telefono);
			if ( bbdd.a�adir_Usuario(user)) {
				System.out.println("Se ha dado de alta en VIDEM");
				user = new Usuario(email,clave);				
				opc=bbdd.login(user);
			}
			else {
				System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo m�s tarde.");
			}
		}
		
		System.out.println("Introduce tu email");
		String email=sc.nextLine();
		System.out.println("Introduce tu contrase�a");
		String clave=sc.nextLine();
		user = new Usuario(email,clave);				
		opc=bbdd.login(user);
		
		if(opc.equalsIgnoreCase("EMPLEADO")) {
			String opc2=bbdd.loginEncargado(user);
			if(opc2=="ADMINISTRADOR") {
				do {
					System.out.println("1� Ver solicitudes");
					System.out.println("2� Dar de alta empleado");
					System.out.println("3� Dar de baja empleado");
					opc3=sc.nextInt();
					switch(opc3) {
					case 1:
						break;
					case 2:
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
						System.out.println("Puesto:");
						String puesto=sc.nextLine();
						int numero=bbdd.numEmpleado(user);
						numero+=1;
						user = new Empleado(email,clave,nombre,domicilio,dni,puesto,telefono,numero);
						bbdd.a�adir_Usuario(user);
						break;
					case 3:
						System.out.println("Correo electronico");
						email=sc.nextLine();
						user = new Empleado(email);
						bbdd.borrar_Usuario(user);
						break;
				}
				}while(opc3!= 4);
			}
			if(opc2.equalsIgnoreCase("ENCARGADO")) {
				do {
					System.out.println("1� A�adir videojuego");
					System.out.println("2� Borrar videojuego");
					System.out.println("3� Recuento de ventas");
					opc3=sc.nextInt();
					switch(opc3) {
					case 1:
						System.out.println("Nuevo c�digo de producto: ");
						String codProducto=sc.nextLine();
						System.out.println("Nuevo t�tulo del videojuego: ");
						String titulo=sc.nextLine();
						System.out.println("Nueva fecha de lanzamiento en formato:dd/mm/aa");
						String fechaPasada=sc.nextLine();
						DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						try{
							lanzamiento=LocalDate.parse(fechaPasada,fechaFormateada);
						}catch(DateTimeParseException e){
							System.out.println("Fecha incorrecta");
						}
						System.out.println("Plataforma del juego nuevo: ");
						String plataforma=sc.nextLine();
						System.out.println("Genero del juego nuevo");
						String genero=sc.nextLine();
						System.out.println("Clasificaci�n PEGI: ");
						String pegi=sc.nextLine();
						System.out.println("Unidades disponibles: ");
						int unidades=sc.nextInt();
						System.out.println("Precio por unidad: ");
						double precio=sc.nextDouble();
						vi = new Videojuego(codProducto,titulo,lanzamiento,plataforma,genero,pegi,unidades,precio);
						if ( bbd.a�adirJuego(vi)) {
							System.out.println("Se ha dado de alta en VIDEM");				
						}
						else {
							System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo m�s tarde.");
						}	
						break;
					case 2:
						System.out.println("C�digo de producto:");
						codProducto=sc.nextLine();
						vi = new Videojuego(codProducto);
						int filas=bbd.borrarVideojuego(vi);
						switch (filas){
							case 0:
								System.out.println("No es un videojuego");
								break;
							case 1: 
								System.out.println("Videojuego eliminado");
								break;
							default:
								System.out.println("En este momento no podemos eliminar. Int�ntalo m�s tarde");
						}
						break;
					case 3:
						break;
				}
				}while(opc3!= 4);
			}
			else {
				
			}
		}
		if(opc.equalsIgnoreCase("CLIENTE")) {
			do {
				System.out.println("1� Buscar videojuego");
				System.out.println("2� Hacer compra");
				System.out.println("3� Historial de compras");
				opc3=sc.nextInt();
				switch(opc3) {
					case 1:
						System.out.println("");
						break;
					case 2:
						System.out.println("1� A�adir a compra");
						break;
					case 3:
						System.out.println();
						break;
				}
			}while(opc3!= 4);
		}
		
		if(!opc.equalsIgnoreCase("EMPLEADO") && !opc.equalsIgnoreCase("CLIENTE") || opc==null) {
			System.out.println("Error, no estas en la base de datos");

		}

	}
	
	public static String Inicio() {
		Scanner sc=new Scanner(System.in);
		Usuario user;
		Videojuego vi;
		BD_Usuario bbdd=new BD_Usuario("videm");
		BD_Videojuego bbd=new BD_Videojuego("videm");
		String opc;
		System.out.println("1� Iniciar Sesi�n");
		System.out.println("2� Registrarse en VIDEM");
		int opcIni=sc.nextInt();
		if(opcIni=='1') {
			sc.nextLine();
			System.out.println("Introduce tu email");
			String email=sc.nextLine();
			System.out.println("Introduce tu contrase�a");
			String clave=sc.nextLine();
			user = new Usuario(email,clave);				
			opc=bbdd.login(user);
			return opc;
		}
		if(opcIni=='2') {
			sc.nextLine();
			System.out.println("Correo electronico");
			String email=sc.nextLine();
			System.out.println("Clave personal:");
			String clave=sc.nextLine();
			System.out.println("Nombre:");
			String nombre=sc.nextLine();
			System.out.println("Dni:");
			String dni=sc.nextLine();
			System.out.println("Domicilio:");
			String domicilio=sc.nextLine();
			System.out.println("Telefono:");
			int telefono=sc.nextInt();
			user = new Usuario(email,clave,nombre,domicilio,dni,"CLIENTE",telefono);
			if ( bbdd.a�adir_Usuario(user)) {
				System.out.println("Se ha dado de alta en VIDEM");
				user = new Usuario(email,clave);				
				opc=bbdd.login(user);
				return opc;
			}
			else {
				System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo m�s tarde.");
				return null;
			}

		}
		return null;
	}

}
