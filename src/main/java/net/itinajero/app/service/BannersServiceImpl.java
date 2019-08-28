package net.itinajero.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.itinajero.app.model.Banner;

@Service
public class BannersServiceImpl implements IBannersService {

	private List<Banner> lista = null;
	
	
	public BannersServiceImpl() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Banner>();
		
		try {
			Banner banner1 = new Banner();
			banner1.setId(1);
			banner1.setTitulo("Estrenos 1");
			banner1.setArchivo("slide1.jpg");
			banner1.setFecha(formatter.parse("20-08-2019"));
			
			Banner banner2 = new Banner();
			banner2.setId(2);
			banner2.setTitulo("Estrenos 2");
			banner2.setArchivo("slide2.jpg");
			
			Banner banner3 = new Banner();
			banner3.setId(3);
			banner3.setTitulo("Estrenos 3");
			banner3.setArchivo("slide3.jpg");
			
			lista.add(banner1);
			lista.add(banner2);
			lista.add(banner3);
		
		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
	
	
	/**
	 * Insertamos un objeto de tipo Banner a la lista
	 */
	@Override
	public void insertar(Banner banner) {
		lista.add(banner);
	}
	

	/**
	 * Regresamos la lista de objetos Banner
	 */
	@Override
	public List<Banner> buscarTodos() {
		return lista;
	}

}
