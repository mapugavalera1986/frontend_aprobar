package org.pugavalera.pndfinal.controladores;

import java.util.List;

import org.pugavalera.pndfinal.models.Cuidador;
import org.pugavalera.pndfinal.models.Participante;
import org.pugavalera.pndfinal.servicios.CuidadorService;
import org.pugavalera.pndfinal.servicios.ParticipanteService;
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
@RequestMapping("/cuidadores")
public class CuidadorController {
	
	Logger logger = LoggerFactory.getLogger(CuidadorService.class);
	
	@Autowired
	private CuidadorService srvc_cuidadores;
	
	@Autowired
	private ParticipanteService srvc_participantes;
	
	@GetMapping
	public ModelAndView ingreso(ModelMap m) {
		List<Cuidador> listar = srvc_cuidadores.listar();
		m.addAttribute("list", listar);
		return new ModelAndView("crud/Cuidadores", m);
	}
	
	@GetMapping("/{id}")
	public ModelAndView revisar(@PathVariable("id") int id, ModelMap m) {
		Cuidador ver = new Cuidador();
		if(id>0) {
			ver = srvc_cuidadores.ver(id);
		}
		m.addAttribute("cuidador", ver);
		return new ModelAndView("crud/crear/Cuidador", m);
	}
	
	@GetMapping("{id}/eliminar")
	public ModelAndView solicitarEliminar(@PathVariable("id") int id, ModelMap m) {
		Cuidador ver = srvc_cuidadores.ver(id);
		List<Participante> listar = srvc_participantes.listarCuidador(id);
		m.addAttribute("cuidador", ver);
		m.addAttribute("participantes", listar);
		return new ModelAndView("crud/eliminar/Cuidador", m);
	}
	
	@PostMapping("/guardar")
	public ModelAndView crear(Cuidador procesar, ModelMap m) {
		srvc_cuidadores.crear(procesar);
		return new ModelAndView("redirect:/cuidadores", m);
	}
	
	@PostMapping("/eliminar")
	public ModelAndView eliminar(Cuidador eliminar, ModelMap m) {
		srvc_cuidadores.eliminar(eliminar.getCuidadorId());
		return new ModelAndView("redirect:/cuidadores", m);
	}
}