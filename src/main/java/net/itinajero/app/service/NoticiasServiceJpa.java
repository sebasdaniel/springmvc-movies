package net.itinajero.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

@Service
public class NoticiasServiceJpa implements INoticiasService {

	@Autowired
	private NoticiasRepository noticiasRepo;
	
	
	@Override
	public List<Noticia> buscarUltimasActivas() {
		return noticiasRepo.findTop3ByEstatusOrderByFechaDesc("Activa");
	}
	

	@Override
	public Page<Noticia> buscarTodasPorPagina(Pageable pageable) {
		return noticiasRepo.findAll(pageable);
	}
	
	
	@Override
	public void guardar(Noticia noticia) {
		noticiasRepo.save(noticia);
	}


	@Override
	public Noticia buscarPorId(int idNoticia) {
		
		Optional<Noticia> optional = noticiasRepo.findById(idNoticia);
		
		if (optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}


	@Override
	public void eliminar(int idNoticia) {
		noticiasRepo.deleteById(idNoticia);
	}
	
}
