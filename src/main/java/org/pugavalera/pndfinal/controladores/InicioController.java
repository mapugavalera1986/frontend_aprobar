package org.pugavalera.pndfinal.controladores;

import org.pugavalera.pndfinal.servicios.CuidadorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InicioController {
	
	Logger logger = LoggerFactory.getLogger(CuidadorService.class);
	
	@GetMapping
	public ModelAndView ingreso(ModelMap m) {
		return new ModelAndView("Inicio", m);
	}
}