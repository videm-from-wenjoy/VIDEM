package principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import bbdd.BD_Compra;
import bbdd.BD_Usuario;
import bbdd.BD_Videojuego;
import modelos.Cliente;
import modelos.Compra;
import modelos.Empleado;
import modelos.Linea;
import modelos.Usuario;
import modelos.Videojuego;
/**
 * 
 * @author Gonzalo Fernandez
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Usuario user=null;
		Videojuego vi;
		Compra co=null;
		Cliente cl=null;
		BD_Usuario bbdd=new BD_Usuario("videm");
		BD_Videojuego bbd=new BD_Videojuego("videm");
		BD_Compra bd=new BD_Compra("videm");
		String opc=null;
		int opc3;
		LocalDate lanzamiento=null;
		String email = null,clave;
		
		do {
			System.out.println("1� Iniciar Sesion");
			System.out.println("2� Registrarse en VIDEM");
			int opcIni=sc.nextInt();
			switch(opcIni) {
				case 1:
					sc.nextLine();
					System.out.println("Introduce tu email");
					email=sc.nextLine();
					System.out.println("Introduce tu contrase�a");
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
					int numero=bbdd.asignarNumCliente(user);
					numero+=1;
					user = new Cliente(email,clave,nombre,domicilio,dni,"CLIENTE",telefono,numero);
					bbdd.a�adir_Usuario(user);
					if ( bbdd.a�adir_Usuario(user)) {
						System.out.println("Se ha dado de alta en VIDEM");
						user = new Usuario(email,clave);				
						opc=bbdd.login(user);
					}
					else {
						System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo m�s tarde.");
					}
					break;
			}
		}while(opc==null);
		
		if(opc.equalsIgnoreCase("EMPLEADO")) {
			user = new Usuario(email);
			String opc2=bbdd.loginEncargado(user);
			if(opc2.equalsIgnoreCase("ADMINISTRADOR")) {
				do {
					System.out.println("1� Dar de alta empleado");
					System.out.println("2� Dar de baja empleado");
					System.out.println("3� Listar usuarios");
					System.out.println("4� Listar empleados");
					System.out.println("5� Listar clientes");
					System.out.println("6� Salir");
					opc3=sc.nextInt();
					switch(opc3) {
					case 1:
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
						System.out.println("Puesto:");
						String puesto=sc.nextLine();
						int numero=bbdd.asignarNumEmpleado(user);
						numero+=1;
						user = new Empleado(email,clave,nombre,domicilio,dni,"EMPLEADO",telefono,numero,puesto);
						bbdd.a�adir_Usuario(user);
						break;
					case 2:
						sc.nextLine();
						System.out.println("Correo electronico");
						email=sc.nextLine();
						user = new Empleado(email);
						bbdd.borrar_Usuario(user);
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
				}
				}while(opc3!= 6);
			}else
			if(opc2.equalsIgnoreCase("ENCARGADO")) {
				do {
					System.out.println("1� A�adir videojuego");
					System.out.println("2� Borrar videojuego");
					System.out.println("3� Recuento de ventas");
					System.out.println("4� Listar videojuegos");
					System.out.println("5� Salir");
					opc3=sc.nextInt();
					switch(opc3) {
					case 1:
						sc.nextLine();
						System.out.println("Nuevo codigo de producto: ");
						String codProducto=sc.nextLine();
						System.out.println("Nuevo titulo del videojuego: ");
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
						System.out.println("Clasificacion PEGI: ");
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
						sc.nextLine();
						System.out.println("Codigo de producto:");
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
								System.out.println("En este momento no podemos eliminar. Intentalo mas tarde");
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
					System.out.println("1� A�adir videojuego");
					System.out.println("2� Borrar videojuego");
					System.out.println("3� Listar videojuego");
					System.out.println("4� Salir");
					opc3=sc.nextInt();
					switch(opc3) {
					case 1:
						sc.nextLine();
						System.out.println("Nuevo codigo de producto: ");
						String codProducto=sc.nextLine();
						System.out.println("Nuevo titulo del videojuego: ");
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
						System.out.println("Clasificacion PEGI: ");
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
							System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo mas tarde.");
						}	
						break;
					case 2:
						sc.nextLine();
						System.out.println("Codigo de producto:");
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
								System.out.println("En este momento no podemos eliminar. Intentalo mas tarde");
						}
						break;
					case 3:
						Vector<Videojuego> v=bbd.listarVideojuegos();
						for(int i=0;i<v.size();i++)
							System.out.println(v.get(i));
						break;
				}
				}while(opc3!= 4);
			}
		}
		if(opc.equalsIgnoreCase("CLIENTE")) {
			do {
				System.out.println("1� Buscar videojuego");
				System.out.println("2� Hacer compra");
				System.out.println("3� Historial de compras");
				System.out.println("4� Salir.");
				opc3=sc.nextInt();
				switch(opc3) {
					case 1:
						sc.nextLine();
						int opcEdit;
						do {
							System.out.println("1� Buscar por codigo de producto:");
							System.out.println("2� Buscar por titulo y plataforma:");
							opcEdit=sc.nextInt();
						}while(opcEdit!=3);
						if(opcEdit==1) {
							System.out.println("Codigo de producto:");
							String codProducto=sc.nextLine();
							bbd.buscarVideojuego(opcEdit,codProducto);
						}
						if(opcEdit==2) {
							System.out.println("Titulo del videojuego:");
							String titulo=sc.nextLine();
							bbd.buscarVideojuego(opcEdit,titulo);
						}
						break;
					case 2:
						sc.nextLine();
						System.out.println("1� A�adir a compra");
						break;
					case 3:
						sc.nextLine();
						System.out.println("Facturas: ");
						System.out.println();
						Vector<Compra> ca=bd.comprasPropias(cl);
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
	
	public static boolean validarDNI(String dni) {
		 
        boolean correcto = false;
        int i = 0;
        int caracter = 0;
        char letra = ' ';
        int miDNI = 0;
        int resto = 0;
        char[] bLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X','B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
 
 
        if(dni.length() == 9 && Character.isLetter(dni.charAt(8))) {
 
            do {
                caracter = dni.codePointAt(i);
                correcto = (caracter > 47 && caracter < 58);
                i++;
            } 
            while(i < dni.length() - 1 && correcto);     
        }
 
        if(correcto) {
            letra = Character.toUpperCase(dni.charAt(8));
            miDNI = Integer.parseInt(dni.substring(0,8));
            resto = miDNI % 23;
            correcto = (letra == bLetra[resto]);
        }
 
        return correcto;
    }

	public static boolean validarTelefono(int tlf) {
		if (tlf>999999999 && tlf<100000000)
			return false;
		return true;
	}
}
	
