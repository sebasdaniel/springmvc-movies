package net.itinajero.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.service.INoticiasService;

@Controller
@RequestMapping("/noticias")
public class NoticiasController {
	
	@Autowired
	private INoticiasService serviceNoticia;
	
	
	/**
	 * Muestra la pagina de inicio de las noticias con la lista de todas.
	 * 
	 * @param model
	 * @param pageable
	 * @return
	 */
	@GetMapping("index")
	public String home(Model model, Pageable pageable) {
		
		Page<Noticia> noticias = serviceNoticia.buscarTodasPorPagina(pageable);
		
		model.addAttribute("noticias", noticias);
		
		return "noticias/listNoticias";
	}

	
	/**
	 * Muestra el formulario para crear una noticia.
	 * 
	 * @return
	 */
	@GetMapping(value = "/create")
	public String crear() {
		return "noticias/formNoticia";
	}
	
	
	/**
	 * Muestra el formulario para editar una noticia.
	 * 
	 * @param idNoticia
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idNoticia, Model model) {
		
		Noticia noticia = serviceNoticia.buscarPorId(idNoticia);
		
		model.addAttribute("noticia", noticia);
		
		return "noticias/formNoticia";
	}
	
	
	/**
	 * Guarda/edita una noticia.
	 * 
	 * @param noticia
	 * @param result
	 * @param attributes
	 * @return
	 */
	@PostMapping(value = "/save")
	public String guardar(@ModelAttribute Noticia noticia, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return "noticias/formNoticia";
		}
		
		// guardamos la noticia
		serviceNoticia.guardar(noticia);
		
		attributes.addFlashAttribute("message", "Noticia guardada correctamente");
		
		return "redirect:/noticias/index?page=0";
	}
	
	
	/**
	 * Elimina una noticia.
	 * 
	 * @param idNoticia
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idNoticia, RedirectAttributes attributes) {
		
		serviceNoticia.eliminar(idNoticia);
		
		attributes.addFlashAttribute("message", "La noticia fue eliminada!");
		
		return "redirect:/noticias/index?page=0";
	}
	
}
