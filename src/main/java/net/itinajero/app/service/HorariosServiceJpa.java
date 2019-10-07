package net.itinajero.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Horario;
import net.itinajero.app.repository.HorariosRepository;

@Service
public class HorariosServiceJpa implements IHorariosService {

	@Autowired
	private HorariosRepository horariosRepo;
	
	@Override
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		return horariosRepo.findByPelicula_IdAndFechaOrderByHora(idPelicula, fecha);
	}
	
	@Override
	public List<Horario> buscarPorIdPeliculaActiva(int idPelicula, Date fecha) {
		return horariosRepo.findByPelicula_IdAndPelicula_EstatusAndFechaOrderByHora(idPelicula, "Activa", fecha);
	}

}
