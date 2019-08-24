package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IPeliculasService;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IPeliculasService servicePelicula;

	
	@GetMapping("/create")
	public String crear() {
		return "peliculas/formPelicula";
	}
	
	
	@PostMapping("/save")
	public String guardar(Pelicula pelicula, BindingResult result) {
		
		if (result.hasErrors()) {
			
			for (ObjectError error: result.getAllErrors()) {
				System.out.println("Error: " + error.getDefaultMessage());
			}
		}
		
		System.out.println("Guardando: " + pelicula);
		
		servicePelicula.insertar(pelicula);
		
		return "peliculas/formPelicula";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
