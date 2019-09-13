package pruebasjparepo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

public class AppPaging {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		NoticiasRepository repo = context.getBean("noticiasRepository", NoticiasRepository.class);
		
		// obtiene todas las entidades utilizando paginacion
		//Page<Noticia> pages = repo.findAll(PageRequest.of(0, 5));
		
		// obtiene todas las entidades utilizando paginacion y ordenamiento
		Page<Noticia> pages = repo.findAll(PageRequest.of(0, 5, Sort.by("titulo")));
		
		for (Noticia n : pages) {
			System.out.println(n);
		}
		
		context.close();

	}

}
