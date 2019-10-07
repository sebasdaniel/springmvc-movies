package net.itinajero.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.itinajero.app.model.Horario;

public interface IHorariosService {
	
	List<Horario> buscarTodos();
	
	Page<Horario> buscarTodosPorPagina(Pageable pageable);
	
	Horario buscarPorId(int idHorario);
	
	List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha);
	
	List<Horario> buscarPorIdPeliculaActiva(int idPelicula, Date fecha);
	
	void guardar(Horario horario);

	void eliminar(int idHorario);
}
