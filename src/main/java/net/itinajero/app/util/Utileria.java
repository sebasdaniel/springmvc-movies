package net.itinajero.app.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {
	
	
	/**
	 * Metodo que regresa una lista de Strings con las fechas siguentes,
	 * segun el parametro count.
	 * 
	 * @param count    Numero de dias siguientes a la fecha actual
	 * @return         Lista con las fechas siguientes.
	 */
	public static List<String> getNextDays(int count) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		// Today's date
		Date start = new Date();
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DAY_OF_MONTH, count); // Next n days from now
		
		Date endDate = cal.getTime();
		
		GregorianCalendar gcal = new GregorianCalendar();
		
		gcal.setTime(start);
		
		List<String> nextDays = new ArrayList<String>();
		
		while (!gcal.getTime().after(endDate)) {
			Date d = gcal.getTime();
			gcal.add(Calendar.DATE, 1);
			nextDays.add(sdf.format(d));
		}
		
		return nextDays;
	}
	
	
	/**
	 * Metodo que guarda una imagen y retorna el nombre de la misma como se guarda.
	 * 
	 * @param multiPart    La variable de archivos recibidos en el request (en este caso imagen binaria)
	 * @param request      Variable con informacion de la peticion
	 * @return             El nombre de la imagen guardada
	 */
	public static String guardarImagen(MultipartFile multiPart, HttpServletRequest request) {
		
		// Obtenemos el nombre original del archivo y cambiamos espacios por guiones
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal = nombreOriginal.replace(" ", "-");
		
		// creamos una nombre con una cadena aleatoria para que sea unico y no sobreescriba otras imagenes
		String nombreFinal = randomAlphaNumeric(8) + nombreOriginal;
		
		// Obtenemos la ruta ABSOLUTA del directorio images
		// apache-tomcat/webapps/cineapp/resources/images/
		String rutaFinal = request.getServletContext().getRealPath("/resources/images/");
		
		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro
			File imageFile = new File(rutaFinal + nombreFinal);
			System.out.println(imageFile.getAbsolutePath());
			
			// Aqui se guarda fisicamente el archivo en el disco duro
			multiPart.transferTo(imageFile);
			
			return nombreFinal;
			
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * Metodo para generar una cadena alfanumerica aleatoria de n caracteres.
	 * 
	 * @param count    El numero de caracteres que contendra la cadena aleatoria
	 * @return         La cadena aleatoria alfanumerica
	 */
	public static String randomAlphaNumeric(int count) {
		
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		
		return builder.toString();
	}

}
