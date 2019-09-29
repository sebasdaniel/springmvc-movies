package net.itinajero.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Pelicula;

//@Service
public class PeliculasServiceImpl implements IPeliculasService {

	private List<Pelicula> lista = null;
	
	
	public PeliculasServiceImpl() {
		
		System.out.println("Creando instancia");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			lista = new LinkedList<>();
			
			Pelicula pelicula1 = new Pelicula();
			pelicula1.setId(1);
			pelicula1.setTitulo("Power Rangers");
			pelicula1.setDuracion(120);
			pelicula1.setClasificacion("B");
			pelicula1.setGenero("Aventura");
			pelicula1.setFechaEstreno(formatter.parse("02-05-2017"));
			pelicula1.setImagen("estreno8.png");
			
			Pelicula pelicula2 = new Pelicula();
			pelicula2.setId(2);
			pelicula2.setTitulo("La bella y la bestia");
			pelicula2.setDuracion(192);
			pelicula2.setClasificacion("A");
			pelicula2.setGenero("Infantil");
			pelicula2.setFechaEstreno(formatter.parse("20-05-2017"));
			pelicula2.setImagen("estreno6.png");
			
			Pelicula pelicula3 = new Pelicula();
			pelicula3.setId(3);
			pelicula3.setTitulo("Contratiempo");
			pelicula3.setDuracion(106);
			pelicula3.setClasificacion("B");
			pelicula3.setGenero("Thriller");
			pelicula3.setFechaEstreno(formatter.parse("28-05-2017"));
			pelicula3.setImagen("estreno7.png");
			
			Pelicula pelicula4 = new Pelicula();
			pelicula4.setId(4);
			pelicula4.setTitulo("Kong La Isla Calavera");
			pelicula4.setDuracion(118);
			pelicula4.setClasificacion("B");
			pelicula4.setGenero("Accion y Aventura");
			pelicula4.setFechaEstreno(formatter.parse("06-06-2017"));
			pelicula4.setEstatus("Inactiva");
			pelicula4.setImagen("estreno4.png");
			
			lista.add(pelicula1);
			lista.add(pelicula2);
			lista.add(pelicula3);
			lista.add(pelicula4);
			
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	
	@Override
	public List<Pelicula> buscarTodas() {
		return lista;
	}


	@Override
	public Pelicula buscarPorId(int idPelicula) {
		
		for (Pelicula p: lista) {
			if(p.getId() == idPelicula) {
				return p;
			}
		}
		
		return null;
	}


	@Override
	public void insertar(Pelicula pelicula) {
		lista.add(pelicula);
	}


	/**
	 * Retorna una lista de generos de pelicula.
	 */
	@Override
	public List<String> buscarGeneros() {
		
		List<String> generos = new LinkedList<>();
		
		generos.add("Accion");
		generos.add("Aventura");
		generos.add("Clasicas");
		generos.add("Comedia Romantica");
		generos.add("Drama");
		generos.add("Terror");
		generos.add("Infantil");
		generos.add("Accion y Aventura");
		generos.add("Romantica");
		generos.add("Ciencia Ficcion");
		
		return generos;
	}


	@Override
	public void eliminar(int idPelicula) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Page<Pelicula> buscarTodas(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
