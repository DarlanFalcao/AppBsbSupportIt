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

import br.com.bsb.model.SubEmpresa;
import br.com.bsb.repository.ClienteRepository;
import br.com.bsb.repository.SubEmpresaRepository;

@Controller
@RequestMapping("/")
public class SubEmpresaController {

	@Autowired
	SubEmpresaRepository subRep;
	@Autowired
	ClienteRepository empRep;
	
	
	@GetMapping("/cadastroSubEmpresa")
	public String cadastroSubEmpresa(Model model) {
		SubEmpresa sub=  new SubEmpresa();
		model.addAttribute("subEmpresa", sub);
		model.addAttribute("clientes", empRep.findAll());
		return "/cadastroSubempresa";
	}
	
	@PostMapping("/salvarSubEmpresa")
	public String salvarSubEmpresa(@ModelAttribute("subEmpresa")SubEmpresa sub,BindingResult result,Model model) {
		
		System.out.println("ERRORS: "+result);
		
		model.addAttribute("razaoSocial",sub.getRazaoSocial());
		model.addAttribute("cnpj", sub.getCnpj());
		model.addAttribute("responsavel",sub.getResponsavel());
		model.addAttribute("endereco", sub.getEndereco());
		model.addAttribute("cliente", sub.getCliente());
		subRep.save(sub);	
		return "redirect:/listarSubEmpresas";
		
	}
	@GetMapping("/listarSubEmpresas")
	public String listarSubEmpresas(Model model) {
		model.addAttribute("subEmpresas", subRep.findAll());
		
		return "/listarSubEmpresa";
		
	}
	@GetMapping("/editSubEmpresa/{idSubEmpresa}")
	public String editarSubEmpresa(@PathVariable("idSubEmpresa")Long id,Model model ) {
		
		
		model.addAttribute("subEmpresa",subRep.findById(id).get() );
		model.addAttribute("clientes", empRep.findAll());
		return "/editarSubempresa";
		
	}
	@PutMapping("/salvarEdicaoSubEmpresa/{idSubEmpresa}")
	public String salvarEdicao(@PathVariable("idSubEmpresa")Long id,@ModelAttribute SubEmpresa sub,BindingResult result, Model model) {
		
		
		
		SubEmpresa subSelecionado = subRep.findById(id).get();
		if(subSelecionado != null) {
			subSelecionado.setCliente(sub.getCliente());
			subSelecionado.setCnpj(sub.getCnpj());
			subSelecionado.setEndereco(sub.getEndereco());
			subSelecionado.setRazaoSocial(sub.getRazaoSocial());
			subSelecionado.setResponsavel(sub.getResponsavel());
		subRep.save(subSelecionado);
		}
		
		return "redirect:/listarSubEmpresas";
		
	}
	@DeleteMapping("/deletarSubEmpresa/{idSubEmpresa}")
	public String deletarSubEmpresa(@PathVariable("idSubEmpresa")Long id, Model model) {
		
		SubEmpresa sub = subRep.findById(id).get();
		if(sub != null) {
			subRep.delete(sub);	
		}
		return "redirect:/listarSubEmpresas";
		
	}
}
