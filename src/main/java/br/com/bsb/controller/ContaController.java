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

import br.com.bsb.model.Conta;
import br.com.bsb.model.ContaDTO;
import br.com.bsb.repository.ContaRepository;
import br.com.bsb.utils.Converter;
@Controller
@RequestMapping("/")
public class ContaController {

	@Autowired
	ContaRepository contaRep;

	@GetMapping("/cadastroConta")
	public String cadastroConta(Model model) {
		ContaDTO conta = new ContaDTO();
		model.addAttribute("conta", conta);
		return "/cadastroConta";
	}

	@GetMapping("/listarContas")
	public String listarConta(Model model) {
		
		for(Conta conta: contaRep.findAll()) {
			System.out.println("PAGO: "+conta.isPago());
			
		}
		
		model.addAttribute("contas", contaRep.findAll());

		return "/listarConta";
	}

	@PostMapping("/salvarConta")
	public String salvarMaterial(@ModelAttribute("conta") ContaDTO contaDTO, BindingResult result, Model model) {

		System.out.println("ERRORS: " + result);
		Conta conta = new Conta();
		model.addAttribute("nome", contaDTO.getNome());
		model.addAttribute("valor", contaDTO.getValor());
		model.addAttribute("descricao", contaDTO.getDescricao());
		model.addAttribute("funcValidou", contaDTO.getFuncValidou());
		model.addAttribute("pago", false);
		conta.setDescricao(contaDTO.getDescricao());
		conta.setFuncValidou(contaDTO.getFuncValidou());
		conta.setNome(contaDTO.getNome());
		conta.setPago(false);
		conta.setValor(Converter.moedaDoubleConverter(contaDTO.getValor()));
		contaRep.save(conta);
		return "redirect:/listarContas";
	}

	@GetMapping("/editConta/{id}")
	public String editarConta(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("conta",Converter.ContatoContaDTO(contaRep.findById(id).get()));
		return "/editarConta";
	}

	@PutMapping("/salvarEdicaoConta/{id}")
	public String salvarEdicaoMaterial(@PathVariable("id") Long id,
			@ModelAttribute("conta") ContaDTO contaDTO, BindingResult result, Model model) {

		Conta contaEscolhido = contaRep.findById(id).get();
		if (contaDTO != null) {
			contaEscolhido.setNome(contaDTO.getNome());
			contaEscolhido.setDescricao(contaDTO.getDescricao());
			contaEscolhido.setFuncValidou(contaDTO.getFuncValidou());
			contaEscolhido.setPago(contaDTO.isPago());
			contaEscolhido.setValor(Converter.moedaDoubleConverter(contaDTO.getValor()));
			contaRep.save(contaEscolhido);
		}
		
		return "redirect:/listarContas";
	}

	@DeleteMapping("/deletarConta/{id}")
	public String deletarMaterial(@PathVariable("id") Long id, Model model) {
		Conta conta = contaRep.findById(id).get();
		if (conta != null) {
			contaRep.delete(conta);
		}
		return "redirect:/listarContas";
	}
	
}
