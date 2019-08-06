package net.itinajero.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.itinajero.app.model.Pelicula;

@Controller
public class HomeController {

	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model) {
		
		List<Pelicula> peliculas = getLista();
//		peliculas.add("Rapido y Furioso");
//		peliculas.add("El aro 2");
//		peliculas.add("Aliens");
		
		model.addAttribute("peliculas", peliculas);
		
		return "home";
	}
	
	
	@RequestMapping(value="/detail")
	public String mostrarDetalle(Model model) {
		
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
			
			Pelicula pelicual1 = new Pelicula();
			pelicual1.setId(1);
			pelicual1.setTitulo("Power Rangers");
			pelicual1.setDuracion(120);
			pelicual1.setClasificacion("B");
			pelicual1.setGenero("Aventura");
			pelicual1.setFechaEstreno(formatter.parse("02-05-2017"));
			
			Pelicula pelicual2 = new Pelicula();
			pelicual2.setId(2);
			pelicual2.setTitulo("La bella y la bestia");
			pelicual2.setDuracion(192);
			pelicual2.setClasificacion("A");
			pelicual2.setGenero("Infantil");
			pelicual2.setFechaEstreno(formatter.parse("20-05-2017"));
			
			Pelicula pelicual3 = new Pelicula();
			pelicual3.setId(3);
			pelicual3.setTitulo("Contratiempo");
			pelicual3.setDuracion(106);
			pelicual3.setClasificacion("B");
			pelicual3.setGenero("Thriller");
			pelicual3.setFechaEstreno(formatter.parse("28-05-2017"));
			
			lista.add(pelicual1);
			lista.add(pelicual2);
			lista.add(pelicual3);
			
			return lista;
			
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
	
}
