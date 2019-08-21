package net.itinajero.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.itinajero.app.model.Pelicula;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {

	
	@GetMapping("/create")
	public String crear() {
		return "peliculas/formPelicula";
	}
	
	
	@PostMapping("/save")
	public String guardar(Pelicula pelicula) {
		
		System.out.println("Guardando: " + pelicula);
		return "peliculas/form Pelicula";
	}
}
