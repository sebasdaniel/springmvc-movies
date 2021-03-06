package pruebasquery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppKeywordOr {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List<Noticia> lista = null;
		
		try { 
			lista = repo.findByEstatusOrFecha("Inactiva", format.parse("2017-09-07 19:00:00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for (Noticia n : lista) {
			System.out.println(n);
		}
		
		context.close();

	}

}
