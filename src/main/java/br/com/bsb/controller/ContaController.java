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

import br.com.bsb.repository.ContaRepository;
@Controller
@RequestMapping("/")
public class ContaController {

	@Autowired
	ContaRepository contaRep;

	@GetMapping("/cadastroConta")
	public String cadastroConta(Model model) {
		Conta conta = new Conta();
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
	public String salvarMaterial(@ModelAttribute("conta") Conta conta, BindingResult result, Model model) {

		System.out.println("ERRORS: " + result);
		model.addAttribute("nome", conta.getNome());
		model.addAttribute("valor", conta.getValor());
		model.addAttribute("descricao", conta.getDescricao());
		model.addAttribute("funcValidou", conta.getFuncValidou());
		model.addAttribute("pago", false);
		contaRep.save(conta);
		return "redirect:/listarContas";
	}

	@GetMapping("/editConta/{id}")
	public String editarConta(@PathVariable("id") Long id, Model model) {
		model.addAttribute("conta", contaRep.findById(id).get());
		return "/editarConta";
	}

	@PutMapping("/salvarEdicaoConta/{id}")
	public String salvarEdicaoMaterial(@PathVariable("id") Long id,
			@ModelAttribute("conta") Conta conta, BindingResult result, Model model) {

		Conta contaEscolhido = contaRep.findById(id).get();
		if (conta != null) {
			contaEscolhido.setNome(conta.getNome());
			contaEscolhido.setDescricao(conta.getDescricao());
			contaEscolhido.setFuncValidou(conta.getFuncValidou());
			contaEscolhido.setPago(conta.isPago());
			contaEscolhido.setValor(conta.getValor());
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
