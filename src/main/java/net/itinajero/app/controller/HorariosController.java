package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Horario;
import net.itinajero.app.service.IPeliculasService;

@Controller
@RequestMapping("/horarios")
public class HorariosController {
	
	@Autowired
	private IPeliculasService servicePelicula;
	
	/**
	 * Metodo para mostrar el formulario para crear un nuevo horario.
	 * 
	 * @return
	 */
	@GetMapping("/create")
	public String crear(@ModelAttribute Horario horario, Model model) {
		
		model.addAttribute("peliculas", servicePelicula.buscarTodas());
		
		return "horarios/formHorario";
	}
	
	
	/**
	 * Metodo para guardar el registro del Horario.
	 * 
	 * @param horario
	 * @param model
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(@ModelAttribute Horario horario, BindingResult result, Model model) {				
		
		if (result.hasErrors()) {
			model.addAttribute("peliculas", servicePelicula.buscarTodas());
			return "horarios/formHorario";
		}
		
		System.out.println(horario);
				
		// De momento, hacemos un redirect al mismo formulario 
		return "redirect:/horarios/create";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
