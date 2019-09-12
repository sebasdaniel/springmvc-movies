package pruebascrudrepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

/**
 * App para crear (persistir) Noticias.
 * 
 * @author sebastian
 *
 */
public class AppCreate {

	public static void main(String[] args) {

		Noticia noticia = new Noticia();
		noticia.setTitulo("Nuevo estreno: Juegos Macabros 8");
		noticia.setDetalle("En el mes de septiembre se estrena la nueva entrega de SAW 8");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		repo.save(noticia);
		
		context.close();
	}

}
