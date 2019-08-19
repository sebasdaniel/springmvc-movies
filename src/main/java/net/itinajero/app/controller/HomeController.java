package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
public class HomeController {
	
	@Autowired
	private IPeliculasService servicePelicula;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model) {
		
		List<String> listaFechas = Utileria.getNextDays(4);
		
		List<Pelicula> peliculas = servicePelicula.buscarTodas();
		
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		
		return "home";
	}
	
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String buscar(Model model, @RequestParam("fecha") String fecha) {
		
		List<String> listaFechas = Utileria.getNextDays(4);
		
		List<Pelicula> peliculas = servicePelicula.buscarTodas();
		
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", fecha);
		
		return "home";
	}
	
	
	@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
	public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") String fecha) {
		
		System.out.println("idPelicula: " + idPelicula + ", Fecha: " + fecha);
		model.addAttribute("pelicula", servicePelicula.buscarPorId(idPelicula));
		
		return "detalle";
	}
	
}
