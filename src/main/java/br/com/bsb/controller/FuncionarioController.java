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
import br.com.bsb.model.Funcao;
import br.com.bsb.model.Funcionario;
import br.com.bsb.model.Setor;
import br.com.bsb.repository.FuncionarioRepository;

@Controller
@RequestMapping("/")
public class FuncionarioController {

	
	@Autowired
	FuncionarioRepository funcRep;
	
	@GetMapping("/cadastroFuncionario")
	public String cadastroFuncionario(Model model) {
		Funcionario funcionario = new Funcionario();
		Funcao funcao = new Funcao();
		Setor setor = new Setor();
		funcionario.setFuncao(funcao);
		funcionario.setSetor(setor);
		model.addAttribute("funcionario", funcionario);
		model.addAttribute("funcao", funcao);
		model.addAttribute("setor", setor);
		
		return "/cadastroFuncionario";
	}
	
	@PostMapping("/salvarFuncionario")
	public String salvarFuncionario(@ModelAttribute("funcionario")Funcionario funcionario,
			BindingResult result, Model model) {
		
		System.out.println("ERRORS: "+result);
		
		model.addAttribute("nome",funcionario.getNome());
		model.addAttribute("username", funcionario.getUsername());
		model.addAttribute("password",funcionario.getPassword());
		model.addAttribute("funcao", funcionario.getFuncao());
		model.addAttribute("setor", funcionario.getSetor());
		
		funcRep.save(funcionario);	
		return "redirect:/listarFuncionarios";
	}
	@GetMapping("/listarFuncionarios")
	public String listarFuncionarios(Model model) {
	
		model.addAttribute("funcionarios", funcRep.findAll());
		
		return "/listarFuncionario";
	}
	@GetMapping("editFuncionario/{idFuncionario}")
	public String editarCliente(@PathVariable("idFuncionario")Long idFuncionario,Model model) {
		
		Funcionario funcionarioSelecionado = new Funcionario();
		model.addAttribute("funcionario", funcRep.findById(idFuncionario).get());
		return "/editarFuncionario";
	}
	@PutMapping("/salvarEdicaoFuncionario/{idFuncionario}")
	public String salvarEdicao(@PathVariable("idFuncionario")Long id, @ModelAttribute Funcionario funcionario,
			BindingResult result,Model model) {
		
		Funcionario funcionarioProcurado = funcRep.findById(id).get();
		if(funcionarioProcurado != null) {
			funcionarioProcurado.setFuncao(funcionario.getFuncao());
			funcionarioProcurado.setNome(funcionario.getNome());
			funcionarioProcurado.setSetor(funcionario.getSetor());
			funcionarioProcurado.setPassword(funcionario.getPassword());
			funcionarioProcurado.setUsername(funcionario.getUsername());
		}
		funcRep.save(funcionarioProcurado);
		return "redirect:/listarFuncionarios";
	}
	
	@DeleteMapping("deletarFuncionario/{id}")
	public String deletar(@PathVariable("id") Long id, Model model) {
		Funcionario funcionario = this.funcRep.findById(id).get();
		
		if (funcionario != null) {
			this.funcRep.delete(funcionario);
			return "redirect:/listarFuncionarios";			
		}

		return "redirect:/listarFuncionarios";
	}
}
