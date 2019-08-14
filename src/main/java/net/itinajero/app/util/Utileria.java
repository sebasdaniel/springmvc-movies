package net.itinajero.app.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

}
