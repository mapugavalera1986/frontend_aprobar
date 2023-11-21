package org.pugavalera.pndfinal.controladores;

import java.util.List;

import org.pugavalera.pndfinal.models.Especialista;
import org.pugavalera.pndfinal.servicios.EspecialistaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/especialistas")
public class EspecialistaController {
	
	Logger logger = LoggerFactory.getLogger(EspecialistaService.class);
	
	@Autowired
	private EspecialistaService srvc_especialistas;
	
	@GetMapping
	public ModelAndView ingreso(ModelMap m) {
		List<Especialista> listar = srvc_especialistas.listar();
		m.addAttribute("list", listar);
		return new ModelAndView("crud/Especialistas", m);
	}
	
	@GetMapping("/{id}")
	public ModelAndView revisar(@PathVariable("id") int id, ModelMap m) {
		Especialista ver = new Especialista();
		if(id>0) {
			ver = srvc_especialistas.ver(id);
		}
		m.addAttribute("especialista", ver);
		return new ModelAndView("crud/crear/Especialista", m);
	}
	
	@PostMapping("/guardar")
	public ModelAndView crear(Especialista procesar, ModelMap m) {
		srvc_especialistas.crear(procesar);
		return new ModelAndView("redirect:/especialistas");
	}
}
