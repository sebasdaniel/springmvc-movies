package net.itinajero.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {
	
	
	@GetMapping("index")
	public String mostrarBienvenida(Authentication authentication) {
		
		// nombre del usuario
		System.out.println("Usuario: " + authentication.getName());
		
		// roles del usuario
		for (GrantedAuthority rol : authentication.getAuthorities()) {
			System.out.println("Rol: " + rol.getAuthority());
		}
		
		return "admin";
	}

	
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {
		
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		
		logoutHandler.logout(request, null, null);
		
		return "redirect:/admin/formLogin";
	}
	
	
	@GetMapping("formLogin")
	public String mostrarLogin() {
		return "formLogin";
	}
}
