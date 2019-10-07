package net.itinajero.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.itinajero.app.model.Banner;
import net.itinajero.app.model.Horario;
import net.itinajero.app.model.Noticia;
import net.itinajero.app.model.Pelicula;
import net.itinajero.app.service.IBannersService;
import net.itinajero.app.service.IHorariosService;
import net.itinajero.app.service.INoticiasService;
import net.itinajero.app.service.IPeliculasService;
import net.itinajero.app.util.Utileria;

@Controller
public class HomeController {
	
	@Autowired
	private IBannersService serviceBanner;
	
	@Autowired
	private IPeliculasService servicePelicula;
	
	@Autowired
	private IHorariosService serviceHorario;
	
	@Autowired
	private INoticiasService serviceNoticia;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goHome() {
		return "home";
	}
	
	
	/**
	 * Muestra la pagina principal actualmente.
	 * 
	 * @param model    El modelo que almacena atributos en forma de mapa y los recibe la vista.
	 * @return         El nombre de la vista.
	 * @throws ParseException 
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String mostrarPrincipal(Model model) throws ParseException {
		
		List<Banner> listaBanners = serviceBanner.buscarTodos();
		List<String> listaFechas = Utileria.getNextDays(4);
		List<Pelicula> peliculas = servicePelicula.buscarPorFechaDeHorario(dateFormat.parse(dateFormat.format(new Date())));
		List<Noticia> ultimasNoticias = serviceNoticia.buscarUltimasActivas();
		
		model.addAttribute("banners", listaBanners);
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("noticias", ultimasNoticias);
		model.addAttribute("fechaBusqueda", dateFormat.format(new Date()));
		
		return "home";
	}
	
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String buscar(Model model, @RequestParam("fecha") String fecha) {
		
		List<Banner> listaBanners = serviceBanner.buscarTodos();
		List<String> listaFechas = Utileria.getNextDays(4);
		List<Pelicula> peliculas = servicePelicula.buscarTodas();
		
		model.addAttribute("banners", listaBanners);
		model.addAttribute("peliculas", peliculas);
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("fechaBusqueda", fecha);
		
		return "home";
	}
	
	
	/**
	 * Busca peliculas por id y fecha.
	 * 
	 * @param model         EL modelo que almacena los datos enviados a la vista.
	 * @param idPelicula    El id de la pelicula.
	 * @param fecha         La fecha en que se proyecta la pelicula.
	 * @return              El nombre de la vista.
	 */
	@RequestMapping(value="/detail/{id}/{fecha}", method=RequestMethod.GET)
	public String mostrarDetalle(Model model, @PathVariable("id") int idPelicula, @PathVariable("fecha") Date fecha) {
		
		List<Horario> horarios = serviceHorario.buscarPorIdPeliculaActiva(idPelicula, fecha);
		
		if (horarios.size() == 0) {
			return "error/404";
		}
		
		model.addAttribute("horarios", horarios);
		model.addAttribute("fechaBusqueda", dateFormat.format(fecha));
		model.addAttribute("pelicula", servicePelicula.buscarPorId(idPelicula));
		
		return "detalle";
	}
	
	
	@GetMapping("/about")
	public String about() {
		return "acerca";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
