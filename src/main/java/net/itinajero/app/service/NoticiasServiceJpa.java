package net.itinajero.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void guardar(Noticia noticia) {
		noticiasRepo.save(noticia);
	}

}
