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

import br.com.bsb.model.Cliente;
import br.com.bsb.model.Endereco;
import br.com.bsb.repository.ClienteRepository;

@Controller
@RequestMapping("/")
public class ClienteController {

	@Autowired
	ClienteRepository cltRep;
	
	@GetMapping("/cadastroCliente")
	public String cadastroCliente(Model model) {
		Cliente cliente = new Cliente();
		Endereco endereco = new Endereco();
		cliente.setEndereco(endereco);
		model.addAttribute("cliente", cliente);
		model.addAttribute("endereco", endereco);
		return "/cadastroCliente";
	}
	
	@PostMapping("/salvarCliente")
	public String salvarCliente(@ModelAttribute("cliente")Cliente cliente,BindingResult result, Model model) {
		System.out.println("ERRORS: "+result);
		
		model.addAttribute("razaoSocial",cliente.getRazaoSocial());
		model.addAttribute("cnpj", cliente.getCnpj());
		model.addAttribute("responsavel",cliente.getResponsavel());
		model.addAttribute("endereco", cliente.getEndereco());
		
		cltRep.save(cliente);	
		return "redirect:/listarClientes";
	}
	@GetMapping("/listarClientes")
	public String listarClientes(Model model) {
	
		model.addAttribute("clientes", cltRep.findAll());
		
		return "/listarCliente";
	}
	@GetMapping("editCliente/{idCliente}")
	public String editarCliente(@PathVariable("idCliente")Long idCliente,Model model) {
		
		Cliente clienteSelecionado = new Cliente();
		model.addAttribute("cliente", cltRep.findById(idCliente).get());
		return "/editarCliente";
	}
	@PutMapping("/salvarEdicaoCliente/{idCliente}")
	public String salvarEdicao(@PathVariable("idCliente")Long id, @ModelAttribute Cliente cliente,BindingResult result,Model model) {
		
		Cliente clienteProcurado = cltRep.findById(id).get();
		if(clienteProcurado != null) {
			clienteProcurado.setCnpj(cliente.getCnpj());
			clienteProcurado.setRazaoSocial(cliente.getRazaoSocial());
			clienteProcurado.setEndereco(cliente.getEndereco());
			clienteProcurado.setResponsavel(cliente.getResponsavel());
		}
		cltRep.save(clienteProcurado);
		return "redirect:/listarClientes";
	}
	
	@DeleteMapping("deletarCliente/{id}")
	public String deletar(@PathVariable("id") Long id, Model model) {
		Cliente cliente = this.cltRep.findById(id).get();
		
		if (cliente != null) {
			this.cltRep.delete(cliente);
			return "redirect:/listarClientes";			
		}

		return "redirect:/listarClientes";
	}
	
	
}
