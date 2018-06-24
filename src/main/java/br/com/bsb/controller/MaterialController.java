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
import br.com.bsb.model.MaterialDTO;
import br.com.bsb.repository.MaterialRepository;
import br.com.bsb.utils.Converter;

@Controller
@RequestMapping("/")
public class MaterialController {

	@Autowired
	MaterialRepository matRep;

	@GetMapping("/cadastroMaterial")
	public String cadastroMaterial(Model model) {
		MaterialDTO material = new MaterialDTO();
		model.addAttribute("material", material);
		return "/cadastroMaterial";
	}

	@GetMapping("/listarMateriais")
	public String listarMateriais(Model model) {
		model.addAttribute("materiais", matRep.findAll());

		return "/listarMaterial";
	}

	@PostMapping("/salvarMaterial")
	public String salvarMaterial(@ModelAttribute("material") MaterialDTO materialDTO, BindingResult result, Model model) {

		
		System.out.println("ERRORS: " + result);
		model.addAttribute("nome", materialDTO.getNome());
		model.addAttribute("valorDeCompra", materialDTO.getValorDeCompra());
		model.addAttribute("valordeRevenda", materialDTO.getValorDeRevenda());
		model.addAttribute("tipoUn", materialDTO.getTipoUn());

		Double valor = Converter.moedaDoubleConverter(materialDTO.getValorDeCompra());
		Double valorRevenda = Converter.moedaDoubleConverter(materialDTO.getValorDeRevenda());
		Material material = new Material();
		material.setNome( materialDTO.getNome());
		material.setTipoUn(materialDTO.getTipoUn());
		material.setValorDeCompra(valor);
		material.setValorDeRevenda(valorRevenda);
		
		matRep.save(material);
		return "redirect:/listarMateriais";
	}

	@GetMapping("/editMaterial/{id}")
	public String editarMaterial(@PathVariable("id") Long id, Model model) {
		
		
		model.addAttribute("material",Converter.materialToMaterialDTO(matRep.findById(id).get()));
		return "/editarMaterial";
	}

	@PutMapping("/salvarEdicaoMaterial/{id}")
	public String salvarEdicaoMaterial(@PathVariable("id") Long id,
			@ModelAttribute("material") MaterialDTO materialDTO, BindingResult result, Model model) {

		Material materialEscolhido = matRep.findById(id).get();
		if (materialDTO != null) {
			materialEscolhido.setNome(materialDTO.getNome());
			materialEscolhido.setTipoUn(materialDTO.getTipoUn());
			materialEscolhido.setValorDeCompra(Converter.moedaDoubleConverter(materialDTO.getValorDeCompra()));
			materialEscolhido.setValorDeRevenda(Converter.moedaDoubleConverter(materialDTO.getValorDeRevenda()));
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
