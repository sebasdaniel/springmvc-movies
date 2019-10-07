package net.itinajero.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Horario;
import net.itinajero.app.repository.HorariosRepository;

@Service
public class HorariosServiceJpa implements IHorariosService {

	@Autowired
	private HorariosRepository horariosRepo;
	
	
	@Override
	public List<Horario> buscarTodos() {
		return horariosRepo.findAll();
	}
	
	
	@Override
	public Page<Horario> buscarTodosPorPagina(Pageable pageable) {
		return horariosRepo.findAll(pageable);
	}
	
	
	@Override
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		return horariosRepo.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
	}
	
	
	@Override
	public List<Horario> buscarPorIdPeliculaActiva(int idPelicula, Date fecha) {
		return horariosRepo.findByPelicula_IdAndPelicula_EstatusAndFechaOrderByHora(idPelicula, "Activa", fecha);
	}


	@Override
	public void guardar(Horario horario) {
		horariosRepo.save(horario);
	}


	@Override
	public Horario buscarPorId(int idHorario) {
		
		Optional<Horario> optional = horariosRepo.findById(idHorario);
		
		if (optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}


	@Override
	public void eliminar(int idHorario) {
		horariosRepo.deleteById(idHorario);
	}
	
}
