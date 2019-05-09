package modelos;
import java.time.LocalDate;

public class Videojuego {
		private String codProducto;
		private String titulo;
		private LocalDate lanzamiento;
		private String plataforma;
		private String genero;
		private String pegi;
		private int unidades;
		private double precio;
		
		
		public Videojuego(String codProducto, String titulo, LocalDate lanzamiento, String plataforma, String genero,
				String pegi, int unidades, double precio) {
			super();
			this.codProducto = codProducto;
			this.titulo = titulo;
			this.lanzamiento = lanzamiento;
			this.plataforma = plataforma;
			this.genero = genero;
			this.pegi = pegi;
			this.unidades = unidades;
			this.precio = precio;
		}
		
		public Videojuego(String codProducto) {
			super();
			this.codProducto = codProducto;
		}

		public String getCodProducto() {
			return codProducto;
		}


		public String getTitulo() {
			return titulo;
		}


		public LocalDate getLanzamiento() {
			return lanzamiento;
		}


		public String getPlataforma() {
			return plataforma;
		}


		public String getGenero() {
			return genero;
		}


		public String getPegi() {
			return pegi;
		}


		public int getUnidades() {
			return unidades;
		}


		public double getPrecio() {
			return precio;
		}

		@Override
		public String toString() {
			return "Videojuego [codProducto=" + codProducto + ", titulo=" + titulo + ", lanzamiento=" + lanzamiento
					+ ", plataforma=" + plataforma + ", genero=" + genero + ", pegi=" + pegi + ", unidades=" + unidades
					+ ", precio=" + precio + "]";
		} 
		
}
