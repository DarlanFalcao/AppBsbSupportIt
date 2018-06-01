package br.com.bsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.bsb.model.Material;
import br.com.bsb.model.TipoServico;
import br.com.bsb.repository.TipoServicoRepository;

@Controller
@RequestMapping("/")
public class TipoServicoController {

	@Autowired
	TipoServicoRepository tipoServRep;

	@GetMapping("/cadastroTipoServico")
	public String cadastroTipoServico(Model model) {
		TipoServico tipoServico = new TipoServico();
		model.addAttribute("tipoServico", tipoServico);
		return "/cadastroTipoServico";
	}

	@GetMapping("/listarTipoServicos")
	public String listarTipoServicos(Model model) {
		model.addAttribute("tipoServicos", tipoServRep.findAll());

		return "/listarTipoServico";
	}

	@PostMapping("/salvarTipoServico")
	public String salvarTipoMaterial(@ModelAttribute("TipoServico") TipoServico tipoServico, BindingResult result, Model model) {

		System.out.println("ERRORS: " + result);
		model.addAttribute("nome", tipoServico.getNome());
		
		model.addAttribute("descricao",tipoServico.getDescricao());

		tipoServRep.save(tipoServico);
		return "redirect:/listarTipoServicos";
	}

	@GetMapping("/editTipoServico/{idTipoServico}")
	public String editarTipoServico(@PathVariable("idTipoServico") Long id, Model model) {
		model.addAttribute("tipoServico", tipoServRep.findById(id).get());
		return "/editarTipoServico";
	}

	@PutMapping("/salvarEdicaoTipoServico/{idTipoServico}")
	public String salvarEdicaoTipoMaterial(@PathVariable("idTipoServico") Long id,
			@ModelAttribute("tipoServico") TipoServico tipoServico, BindingResult result, Model model) {

		TipoServico tipoServicoEscolhido = tipoServRep.findById(id).get();
		if (tipoServico != null) {
			tipoServicoEscolhido.setNome(tipoServico.getNome());
			
			tipoServicoEscolhido.setDescricao(tipoServico.getDescricao());
			tipoServRep.save(tipoServicoEscolhido);
			
		}
		
		return "redirect:/listarTipoServicos";
	}

	@DeleteMapping("/deletarTipoServico/{tipoServico}")
	public String deletarTipoMaterial(@PathVariable("tipoServico") Long id, Model model) {
		TipoServico tipoServico = tipoServRep.findById(id).get();
		if (tipoServico != null) {
			tipoServRep.delete(tipoServico);
		}
		return "redirect:/listarTipoServicos";
	}
	
}
