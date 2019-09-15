package net.itinajero.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Noticia;

@Repository
public interface NoticiasRepository extends JpaRepository<Noticia, Integer> {
	
	List<Noticia> findByEstatus(String status);
	
	List<Noticia> findByFecha(Date fecha);
	
	List<Noticia> findByEstatusAndFecha(String status, Date fecha);
	
	List<Noticia> findByEstatusOrFecha(String status, Date fecha);

}
