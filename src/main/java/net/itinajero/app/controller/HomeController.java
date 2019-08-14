package net.itinajero.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.util.Utileria;

@Controller
public class HomeController {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model) {
		
		List<String> listaFechas = Utileria.getNextDays(4);
		
		List<Pelicula> peliculas = getLista();
//		peliculas.add("Rapido y Furioso");
//		peliculas.add("El aro 2");
//		peliculas.add("Aliens");
		
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		
		return "home";
	}
	
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String buscar(Model model, @RequestParam("fecha") String fecha) {
		
		List<String> listaFechas = Utileria.getNextDays(4);
		
		List<Pelicula> peliculas = getLista();
		
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", fecha);
		
		return "home";
	}
	
	
	//@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	//public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") String fecha) {
	public String mostrarDetalle(Model model, @RequestParam("idMovie") int idPelicula, @RequestParam("fecha") String fecha) {
		
		System.out.println("idPelicula: " + idPelicula + ", Fecha: " + fecha);
		
		String tituloPelicula = "Rapidos y Furiosos";
		int duracion = 136;
		double precioEntrada = 50;
		
		model.addAttribute("titulo", tituloPelicula);
		model.addAttribute("duracion", duracion);
		model.addAttribute("precio", precioEntrada);
		
		return "detalle";
	}
	
	
	// Metodo para generar una lista de objetos de modelo (Pelicula)
	private List<Pelicula> getLista() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		List<Pelicula> lista = null;
		
		try {
			lista = new LinkedList<>();
			
			Pelicula pelicula1 = new Pelicula();
			pelicula1.setId(1);
			pelicula1.setTitulo("Power Rangers");
			pelicula1.setDuracion(120);
			pelicula1.setClasificacion("B");
			pelicula1.setGenero("Aventura");
			pelicula1.setFechaEstreno(formatter.parse("02-05-2017"));
			pelicula1.setImagen("estreno8.png");
			
			Pelicula pelicula2 = new Pelicula();
			pelicula2.setId(2);
			pelicula2.setTitulo("La bella y la bestia");
			pelicula2.setDuracion(192);
			pelicula2.setClasificacion("A");
			pelicula2.setGenero("Infantil");
			pelicula2.setFechaEstreno(formatter.parse("20-05-2017"));
			pelicula2.setImagen("estreno6.png");
			
			Pelicula pelicula3 = new Pelicula();
			pelicula3.setId(3);
			pelicula3.setTitulo("Contratiempo");
			pelicula3.setDuracion(106);
			pelicula3.setClasificacion("B");
			pelicula3.setGenero("Thriller");
			pelicula3.setFechaEstreno(formatter.parse("28-05-2017"));
			pelicula3.setImagen("estreno7.png");
			
			Pelicula pelicula4 = new Pelicula();
			pelicula4.setId(4);
			pelicula4.setTitulo("Kong La Isla Calavera");
			pelicula4.setDuracion(118);
			pelicula4.setClasificacion("B");
			pelicula4.setGenero("Accion y Aventura");
			pelicula4.setFechaEstreno(formatter.parse("06-06-2017"));
			pelicula4.setEstatus("Inactiva");
			pelicula4.setImagen("estreno4.png");
			
			lista.add(pelicula1);
			lista.add(pelicula2);
			lista.add(pelicula3);
			lista.add(pelicula4);
			
			return lista;
			
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
	
}
