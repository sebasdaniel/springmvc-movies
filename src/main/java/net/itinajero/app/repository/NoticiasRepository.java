package net.itinajero.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Noticia;

@Repository
public interface NoticiasRepository extends CrudRepository<Noticia, Integer> {

}
