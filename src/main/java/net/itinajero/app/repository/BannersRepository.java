package net.itinajero.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Banner;

@Repository
public interface BannersRepository extends JpaRepository<Banner, Integer> {

}
