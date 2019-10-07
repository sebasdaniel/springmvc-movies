package net.itinajero.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.itinajero.app.model.Noticia;

public interface INoticiasService {
	
	List<Noticia> buscarUltimasActivas();
	
	Page<Noticia> buscarTodasPorPagina(Pageable pageable);
	
	void guardar(Noticia noticia);

	Noticia buscarPorId(int idNoticia);

	void eliminar(int idNoticia);
}
