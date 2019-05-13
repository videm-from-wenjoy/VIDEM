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
		Empleado emp=null;
		BD_Usuario bbdd=new BD_Usuario("videm");
		BD_Videojuego bbd=new BD_Videojuego("videm");
		BD_Compra bd=new BD_Compra("videm");
		String opc=null;
		int opc3;
		LocalDate lanzamiento=null;
		String email,clave;
		
		do {
			System.out.println("1º Iniciar Sesion");
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
					int numero=bbdd.asignarNumCliente(user);
					numero+=1;
					user = new Usuario(email,clave,nombre,domicilio,dni,"CLIENTE",telefono);
					bbdd.añadir_Usuario(user);
					user = new Cliente(email,numero);
					bbdd.añadir_Cliente(cl);
					if ( bbdd.añadir_Cliente(cl)) {
						System.out.println("Se ha dado de alta en clientes de VIDEM");
						user = new Usuario(email,clave);				
						opc=bbdd.login(user);
					}
					else {
						System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo mï¿½s tarde.");
					}
					break;
			}
		}while(opc==null);
		
		if(opc.equalsIgnoreCase("EMPLEADO")) {
			String opc2=bbdd.loginEncargado(user);
			if(opc2.equalsIgnoreCase("ADMINISTRADOR")) {
				do {
					System.out.println("1º Dar de alta empleado");
					System.out.println("2º Dar de baja empleado");
					System.out.println("3º Listar usuarios");
					System.out.println("4º Listar empleados");
					System.out.println("5º Listar clientes");
					System.out.println("6º Salir");
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
						user = new Usuario(email,clave,nombre,domicilio,dni,"EMPLEADO",telefono);
						bbdd.añadir_Usuario(user);
						emp = new Empleado(email,numero,puesto);
						bbdd.añadir_Empleado(emp);
						if ( bbdd.añadir_Empleado(emp)) {
							System.out.println("Se ha dado de alta en empleados de VIDEM");
							user = new Usuario(email,clave);				
							opc=bbdd.login(user);
						}
						else {
							System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo mas tarde.");
						}
						break;
					case 2:
						sc.nextLine();
						System.out.println("Correo electronico");
						email=sc.nextLine();
						user = new Empleado(email);
						bbdd.borrar_Usuario(user);
						break;
					case 3:
						Vector <Usuario> usu2= bbdd.listarUsuarios();
						for(int i=0;i<usu2.size();i++) {
							System.out.println(usu2.get(i));
						}
						break;
					case 4:
						Vector <Empleado> emp2= bbdd.listarEmpleados();
						for(int i=0;i<emp2.size();i++) {
							System.out.println(emp2.get(i));
						}
						break;
					case 5:
						Vector<Cliente> cli2= bbdd.listarClientes(); 
						for(int i=0;i<cli2.size();i++) {
							System.out.println(cli2.get(i));
						}
						break;
				}
				}while(opc3!= 6);
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
						if ( bbd.añadirJuego(vi)) {
							System.out.println("Se ha dado de alta en VIDEM");				
						}
						else {
							System.out.println("No se ha podido dar de alta en VIDEM, por favor intentelo mï¿½s tarde.");
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
			if(opc2.equalsIgnoreCase("EMPLEADO")) {
				do {
					System.out.println("1º Añadir videojuego");
					System.out.println("2º Borrar videojuego");
					System.out.println("3º Listar videojuego");
					System.out.println("4º Salir");
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
						if ( bbd.añadirJuego(vi)) {
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
				System.out.println("1º Buscar videojuego");
				System.out.println("2º Hacer compra");
				System.out.println("3º Historial de compras");
				System.out.println("4º Salir.");
				opc3=sc.nextInt();
				switch(opc3) {
					case 1:
						int opcEdit;
						do {
							System.out.println("1º Buscar por codigo de producto:");
							System.out.println("2º Buscar por titulo y plataforma:");
							opcEdit=sc.nextInt();
							sc.nextLine();
						}while(opcEdit!=1 && opcEdit!=2);
						if(opcEdit==1) {
							System.out.println("Codigo de producto:");
							String codProducto=sc.nextLine();
							System.out.println(bbd.buscarVideojuego(opcEdit,codProducto));
						}
						if(opcEdit==2) {
							System.out.println("Titulo del videojuego:");
							String titulo=sc.nextLine();
							System.out.println(bbd.buscarVideojuego(opcEdit,titulo));
						}
						break;
					case 2:
						sc.nextLine();
						System.out.println("1º Añadir a compra");
						break;
					case 3:
						sc.nextLine();
						System.out.println("Facturas: ");
						cl = new Cliente(user.getEmail());
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
	
