package net.itinajero.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

//@Service
public class NoticiasServiceImpl implements INoticiasService {
	
	@Override
	public void guardar(Noticia noticia) {
		System.out.println("Guardando: " + noticia);
	}

	@Override
	public List<Noticia> buscarUltimasActivas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Noticia> buscarTodasPorPagina(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Noticia buscarPorId(int idNoticia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(int idNoticia) {
		// TODO Auto-generated method stub
		
	}

}
