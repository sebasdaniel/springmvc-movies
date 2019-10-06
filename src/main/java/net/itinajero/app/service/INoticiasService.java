package net.itinajero.app.service;

import java.util.List;

import net.itinajero.app.model.Noticia;

public interface INoticiasService {
	
	List<Noticia> buscarUltimasActivas();
	void guardar(Noticia noticia);
}
