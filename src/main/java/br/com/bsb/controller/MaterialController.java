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
import br.com.bsb.repository.MaterialRepository;

@Controller
@RequestMapping("/")
public class MaterialController {

	@Autowired
	MaterialRepository matRep;

	@GetMapping("/cadastroMaterial")
	public String cadastroMaterial(Model model) {
		Material material = new Material();
		model.addAttribute("material", material);
		return "/cadastroMaterial";
	}

	@GetMapping("/listarMateriais")
	public String listarMateriais(Model model) {
		model.addAttribute("materiais", matRep.findAll());

		return "/listarMaterial";
	}

	@PostMapping("/salvarMaterial")
	public String salvarMaterial(@ModelAttribute("material") Material material, BindingResult result, Model model) {

		System.out.println("ERRORS: " + result);
		model.addAttribute("nome", material.getNome());
		model.addAttribute("valorDeCompra", material.getValorDeCompra());
		model.addAttribute("valordeRevenda", material.getValorDeRevenda());
		model.addAttribute("tipoUn", material.getTipoUn());

		matRep.save(material);
		return "redirect:/listarMateriais";
	}

	@GetMapping("/editMaterial/{id}")
	public String editarMaterial(@PathVariable("id") Long id, Model model) {
		model.addAttribute("material", matRep.findById(id).get());
		return "/editarMaterial";
	}

	@PutMapping("/salvarEdicaoMaterial/{id}")
	public String salvarEdicaoMaterial(@PathVariable("id") Long id,
			@ModelAttribute("material") Material material, BindingResult result, Model model) {

		Material materialEscolhido = matRep.findById(id).get();
		if (material != null) {
			materialEscolhido.setNome(material.getNome());
			materialEscolhido.setTipoUn(material.getTipoUn());
			materialEscolhido.setValorDeCompra(material.getValorDeCompra());
			materialEscolhido.setValorDeRevenda(material.getValorDeRevenda());
			matRep.save(materialEscolhido);
			
		}
		
		return "redirect:/listarMateriais";
	}

	@DeleteMapping("/deletarMaterial/{id}")
	public String deletarMaterial(@PathVariable("id") Long id, Model model) {
		Material material = matRep.findById(id).get();
		if (material != null) {
			matRep.delete(material);
		}
		return "redirect:/listarMateriais";
	}
}
