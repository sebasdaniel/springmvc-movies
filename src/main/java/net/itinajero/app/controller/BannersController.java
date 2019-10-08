package net.itinajero.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.itinajero.app.model.Banner;
import net.itinajero.app.service.IBannersService;
import net.itinajero.app.util.Utileria;

@Controller
@RequestMapping("/banners")
public class BannersController {

	@Autowired
	private IBannersService serviceBanner;
	
	
	/**
	 * Metodo para mostrar el listado de banners.
	 * 
	 * @param model    La variable model sirve para pasar datos a la vista
	 * @return         El nombre de la vista a renderizar
	 */
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		
		List<Banner> listaBanners = serviceBanner.buscarTodos();
		
		model.addAttribute("banners", listaBanners);
		
		return "banners/listBanners";
	}
	
	
	/**
	 * Metodo para mostrar el formulario para crear un nuevo Banner.
	 * 
	 * @return    El nombre de la vista a renderizar
	 */
	@GetMapping("create")
	public String crear(@ModelAttribute Banner banner) {
		return "banners/formBanner";
	}
	
	
	@GetMapping("edit/{id}")
	public String editar(@PathVariable("id") int idBanner, Model model) {
		
		Banner banner = serviceBanner.buscarPorId(idBanner);
		
		model.addAttribute("banner", banner);
		
		return "banners/formBanner";
	}
	
	
	/**
	 * Metodo para guardar el objeto de modelo de tipo Banner.
	 * 
	 * @return    El nombre de la ruta a redireccionar
	 */
	@PostMapping("save")
	public String guardar(Banner banner, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagen") MultipartFile multiPart, HttpServletRequest request) {
		
		// validamos errores
		if (result.hasErrors()) {
			return "banners/formBanner";
		}
		
		// validamos archivo de imagen
		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarImagen(multiPart, request);
			banner.setArchivo(nombreImagen);
		}
		
		serviceBanner.insertar(banner);
		
		attributes.addFlashAttribute("message", "El banner se guardo correctamente");
		
		return "redirect:/banners/index";
	}
	
	
	@GetMapping("delete/{id}")
	public String eliminar(@PathVariable("id") int idBanner, RedirectAttributes attributes) {
		
		// se elimina el banner
		serviceBanner.eliminar(idBanner);
		
		attributes.addFlashAttribute("message", "El banner fue eliminado!");
		
		return "redirect:/banners/index";
	}
	
}
