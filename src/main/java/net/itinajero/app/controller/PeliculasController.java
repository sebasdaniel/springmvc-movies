package net.itinajero.app.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IDetallesService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping("/peliculas")
public class PeliculasController {
	
	@Autowired
	private IPeliculasService servicePelicula;
	
	@Autowired
	private IDetallesService serviceDetalle;
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		List<Pelicula> lista = servicePelicula.buscarTodas();
		
		model.addAttribute("peliculas", lista);
		
		return "peliculas/listPeliculas";
	}

	
	@GetMapping("/create")
	public String crear(@ModelAttribute Pelicula pelicula, Model model) {
		
		return "peliculas/formPelicula";
	}
	
	
	@PostMapping("/save")
	public String guardar(@ModelAttribute Pelicula pelicula, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			System.out.println("Existen errores");
			return "peliculas/formPelicula";
		}
		
		if (!multiPart.isEmpty()) {
			String nombreImage = Utileria.guardarImagen(multiPart, request);
			pelicula.setImagen(nombreImage);
		}
		
		System.out.println("Detalle antes: " + pelicula.getDetalle());
		
		serviceDetalle.insertar(pelicula.getDetalle());
		
		System.out.println("Detalle despues: " + pelicula.getDetalle());
		
		servicePelicula.insertar(pelicula);
		
		attributes.addFlashAttribute("message", "El registro fue guardado exitosamente");
		
		//return "peliculas/formPelicula";
		return "redirect:/peliculas/index";
	}
	
	
	@GetMapping("/edit/{id}")
	public String editar(Model model, @PathVariable("id") int idPelicula) {
		
		Pelicula pelicula = servicePelicula.buscarPorId(idPelicula);
		
		model.addAttribute("pelicula", pelicula);
		
		return "peliculas/formPelicula";
	}
	
	
	@ModelAttribute("generos")
	public List<String> getGeneros() {
		return servicePelicula.buscarGeneros();
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
