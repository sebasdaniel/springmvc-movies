package net.itinajero.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Horario;
import net.itinajero.app.service.IHorariosService;
import net.itinajero.app.service.IPeliculasService;

@Controller
@RequestMapping("/horarios")
public class HorariosController {
	
	@Autowired
	private IPeliculasService servicePelicula;
	
	@Autowired
	private IHorariosService serviceHorario;
	
	
	/**
	 * Muestra la pagina principal de horarios con el listado de todos los horarios.
	 * 
	 * @param model
	 * @param pageable
	 * @return
	 */
	@GetMapping("/index")
	public String home(Model model, Pageable pageable) {
		
		Page<Horario> horarios = serviceHorario.buscarTodosPorPagina(pageable);
		
		model.addAttribute("horarios", horarios);
		
		return "horarios/listHorarios";
	}
	
	
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
	 * Muestra el formulario para editar el horario.
	 * 
	 * @param idHorario
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idHorario, Model model) {
		
		Horario horario = serviceHorario.buscarPorId(idHorario);
		
		model.addAttribute("horario", horario);
		model.addAttribute("peliculas", servicePelicula.buscarTodas());
		
		return "horarios/formHorario";
	}
	
	
	/**
	 * Metodo para guardar/editar el registro del Horario.
	 * 
	 * @param horario
	 * @param result
	 * @param model
	 * @param attributes
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(@ModelAttribute Horario horario, BindingResult result, Model model, RedirectAttributes attributes) {				
		
		if (result.hasErrors()) {
			model.addAttribute("peliculas", servicePelicula.buscarTodas());
			return "horarios/formHorario";
		}
		
		serviceHorario.guardar(horario);
		
		attributes.addFlashAttribute("message", "El registro fue guardado exitosamente!");
				
		return "redirect:/horarios/index?page=0";
	}
	
	
	/**
	 * Elimina un horario.
	 * 
	 * @param idHorario
	 * @param model
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idHorario, Model model, RedirectAttributes attributes) {
		
		// se elimina el horario
		serviceHorario.eliminar(idHorario);
		
		attributes.addFlashAttribute("message", "Horario eliminado correctamente");
		
		return "redirect:/horarios/index?page=0";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
