package net.itinajero.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Pelicula;

@Repository
public interface PeliculasRepository extends JpaRepository<Pelicula, Integer> {

	@Query("select h.pelicula from Horario h where h.pelicula.estatus='Activa' and h.fecha=:fecha group by h.pelicula")
	List<Pelicula> buscarTodasPorFechaHorario(@Param("fecha") Date fecha);
	
}
