package net.itinajero.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Banner;
import net.itinajero.app.repository.BannersRepository;

@Service
public class BannersServiceJpa implements IBannersService {
	
	@Autowired
	private BannersRepository bannersRepo;
	
	
	@Override
	public void insertar(Banner banner) {
		bannersRepo.save(banner);
	}
	

	@Override
	public List<Banner> buscarTodos() {
		List<Banner> banners = bannersRepo.findAll();
		return banners;
	}

	
	@Override
	public Banner buscarPorId(int idBanner) {
		
		Optional<Banner> optional = bannersRepo.findById(idBanner);
		
		if (optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}


	@Override
	public void eliminar(int idBanner) {
		bannersRepo.deleteById(idBanner);
	}
	
}
