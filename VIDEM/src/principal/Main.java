package principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import bbdd.BD_Compra;
import bbdd.BD_Usuario;
import bbdd.BD_Videojuego;
import modelos.Compra;
import modelos.Empleado;
import modelos.Linea;
import modelos.Usuario;
import modelos.Videojuego;
/**
 * 
 * @author Gonzalo Fernández
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Usuario user=null;
		Videojuego vi;
		Compra co=null;
		BD_Usuario bbdd=new BD_Usuario("videm");
		BD_Videojuego bbd=new BD_Videojuego("videm");
		BD_Compra bd=new BD_Compra("videm");
		String opc=null;
		int opc3;
		LocalDate lanzamiento=null;
		String email,clave;
		
		do {
			System.out.println("1º Iniciar Sesión");
			System.out.println("2º Registrarse en VIDEM");
			int opcIni=sc.nextInt();
			switch(opcIni) {
				case 1:
					sc.nextLine();
					System.out.println("Introduce tu email");
					email=sc.nextLine();
					System.out.println("Introduce tu contraseña");
					clave=sc.nextLine();
					user = new Usuario(email,clave);				
					opc=bbdd.login(user);
					break;
				case 2:
					sc.nextLine();
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
					if ( bbdd.añadir_Usuario(user)) {
						System.out.println("Se ha dado de alta en VIDEM");
						user = new Usuario(email,clave);				
						opc=bbdd.login(user);
					}
					else {
						System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo más tarde.");
					}
					break;
			}
		}while(opc==null);
		
		if(opc.equalsIgnoreCase("EMPLEADO")) {
			String opc2=bbdd.loginEncargado(user);
			if(opc2=="ADMINISTRADOR") {
				do {
					System.out.println("1º Dar de alta empleado");
					System.out.println("2º Dar de baja empleado");
					System.out.println("3º Salir");
					opc3=sc.nextInt();
					switch(opc3) {
					case 1:
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
						bbdd.añadir_Usuario(user);
						break;
					case 2:
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
					System.out.println("1º Añadir videojuego");
					System.out.println("2º Borrar videojuego");
					System.out.println("3º Recuento de ventas");
					System.out.println("4º Listar videojuegos");
					System.out.println("5º Salir");
					opc3=sc.nextInt();
					switch(opc3) {
					case 1:
						System.out.println("Nuevo código de producto: ");
						String codProducto=sc.nextLine();
						System.out.println("Nuevo título del videojuego: ");
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
						System.out.println("Clasificación PEGI: ");
						String pegi=sc.nextLine();
						System.out.println("Unidades disponibles: ");
						int unidades=sc.nextInt();
						System.out.println("Precio por unidad: ");
						double precio=sc.nextDouble();
						vi = new Videojuego(codProducto,titulo,lanzamiento,plataforma,genero,pegi,unidades,precio);
						if ( bbd.añadirJuego(vi)) {
							System.out.println("Se ha dado de alta en VIDEM");				
						}
						else {
							System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo más tarde.");
						}	
						break;
					case 2:
						System.out.println("Código de producto:");
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
								System.out.println("En este momento no podemos eliminar. Inténtalo más tarde");
						}
						break;
					case 3:
						Vector<Compra> ca=bd.compras();
						for(int i=0;i<ca.size();i++) {
							System.out.println("Facturas:");
							System.out.println(ca.get(i));
							Vector<Linea> la=bd.lineas(co);
							for(i=0;i<la.size();i++) {
								System.out.println("Productos:");
								System.out.println(la.get(i));
							}
						}
						break;
					case 4:
						Vector<Videojuego> v=bbd.listarVideojuegos();
						for(int i=0;i<v.size();i++)
							System.out.println(v.get(i));
						break;
					}
				}while(opc3!= 5);
			}
			else {
				do {
					System.out.println("1º Añadir videojuego");
					System.out.println("2º Borrar videojuego");
					System.out.println("3º Salir");
					opc3=sc.nextInt();
					switch(opc3) {
					case 1:
						System.out.println("Nuevo código de producto: ");
						String codProducto=sc.nextLine();
						System.out.println("Nuevo título del videojuego: ");
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
						System.out.println("Clasificación PEGI: ");
						String pegi=sc.nextLine();
						System.out.println("Unidades disponibles: ");
						int unidades=sc.nextInt();
						System.out.println("Precio por unidad: ");
						double precio=sc.nextDouble();
						vi = new Videojuego(codProducto,titulo,lanzamiento,plataforma,genero,pegi,unidades,precio);
						if ( bbd.añadirJuego(vi)) {
							System.out.println("Se ha dado de alta en VIDEM");				
						}
						else {
							System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo más tarde.");
						}	
						break;
					case 2:
						System.out.println("Código de producto:");
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
								System.out.println("En este momento no podemos eliminar. Inténtalo más tarde");
						}
						break;
				}
				}while(opc3!= 3);
			}
		}
		if(opc.equalsIgnoreCase("CLIENTE")) {
			do {
				System.out.println("1º Buscar videojuego");
				System.out.println("2º Hacer compra");
				System.out.println("3º Historial de compras");
				System.out.println("4º Salir.");
				opc3=sc.nextInt();
				switch(opc3) {
					case 1:
						System.out.println("");
						break;
					case 2:
						System.out.println("1º Añadir a compra");
						break;
					case 3:
						System.out.println("Facturas: ");
						System.out.println();
						Vector<Compra> ca=bd.compras();
						for(int i=0;i<ca.size();i++) {
							System.out.println(ca.get(i));
							Vector<Linea> la=bd.lineas(co);
							for(i=0;i<la.size();i++) {
								System.out.println(la.get(i));
							}
						}
						break;
				}
			}while(opc3!= 4);
		}
		
		if(!opc.equalsIgnoreCase("EMPLEADO") && !opc.equalsIgnoreCase("CLIENTE") || opc==null) {
			System.out.println("Error, no estas en la base de datos");

		}

	}
	
}