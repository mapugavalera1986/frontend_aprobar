package org.pugavalera.pndfinal.controladores;

import java.util.List;

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
@RequestMapping("/participantes")
public class ParticipanteController {
	
	Logger logger = LoggerFactory.getLogger(ParticipanteService.class);
	
	@Autowired
	private ParticipanteService srvc_participantes;
	
	@Autowired
	private CuidadorService srvc_cuidadores;
	
	@GetMapping
	public ModelAndView ingreso(ModelMap m) {
		List<Participante> listar = srvc_participantes.listar();
		m.addAttribute("list", listar);
		return new ModelAndView("crud/Participantes", m);
	}
	
	@GetMapping("/{id}")
	public ModelAndView revisar(@PathVariable("id") int id, ModelMap m) {
		Participante ver = new Participante();
		if(id>0) {
			ver = srvc_participantes.ver(id);
		}
		m.addAttribute("participante", ver);
		m.addAttribute("elegir_cuidadores", srvc_cuidadores.listar());
		System.out.println("Mira: " + ver.toString());
		return new ModelAndView("crud/crear/Participante", m);
	}
	
	@PostMapping("/guardar")
	public ModelAndView crear(Participante procesar, ModelMap m) {
		System.out.println("Mira esto:" + procesar.toString());
		System.out.println("Mira esto:" + procesar.toString());
		return new ModelAndView("redirect:/participantes");
	}
	
	@GetMapping("{id}/eliminar")
	public ModelAndView solicitarEliminar(@PathVariable("id") int id, ModelMap m) {
		Participante ver = srvc_participantes.ver(id);
		m.addAttribute("participante", ver);
		return new ModelAndView("crud/eliminar/Participante", m);
	}
	
	@PostMapping("/eliminar")
	public ModelAndView eliminar(Participante eliminar, ModelMap m) {
		srvc_participantes.eliminar(eliminar.getParticipanteId());
		return new ModelAndView("redirect:/participantes", m);
	}
}
