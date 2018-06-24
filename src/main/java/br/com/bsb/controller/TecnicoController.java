package br.com.bsb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.bsb.model.DadosBancarios;
import br.com.bsb.model.Endereco;
import br.com.bsb.model.Rat;
import br.com.bsb.model.Tecnico;
import br.com.bsb.model.Telefone;
import br.com.bsb.repository.DadosBancariosRepository;
import br.com.bsb.repository.EnderecoRepository;
import br.com.bsb.repository.RatRepository;
import br.com.bsb.repository.TecnicoRepository;
import br.com.bsb.repository.TelefoneRepository;

@Controller
@RequestMapping(path = "/")
public class TecnicoController {

	@Autowired
	private TecnicoRepository tecRepository;
	@Autowired
	private EnderecoRepository endRepository;
	@Autowired
	private TelefoneRepository telRep;
	@Autowired
	private DadosBancariosRepository dadosRep;

	@Autowired
	private RatRepository ratRep;
	
	@GetMapping("/cadastroTecnico")
	public String chamarCadastroTecnico(Model model) {
		Tecnico tecnico = new Tecnico();
		String dataNascimento ="";
		DadosBancarios dados = new DadosBancarios();
		Endereco endereco = new Endereco();
		Telefone tel1 = new Telefone();
		Telefone tel2 = new Telefone();
		List<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(tel1);
		telefones.add(tel2);
		tecnico.setTelefone(telefones);
		tecnico.setDadosBancarios(dados);
		tecnico.setEndereco(endereco);
		model.addAttribute("tecnico", tecnico);
		model.addAttribute("dataNascimnto", dataNascimento);
		return "cadastroTecnico";
	}

	@PostMapping("/salvarTecnico")
	public String newTecnico(@ModelAttribute("tecnico") Tecnico tecnico,
			BindingResult result, Model model,RedirectAttributes red) {
		
		
		System.out.println(result);
		System.out.println("TESTE"+tecnico.getDataNasc());
		
		model.addAttribute("nome", tecnico.getNome());
		model.addAttribute("email", tecnico.getEmail());
		model.addAttribute("numDoc", tecnico.getNumDoc());
		model.addAttribute("dataNasc", tecnico.getDataNasc());
		model.addAttribute("tipoPessoa", tecnico.getTipoPessoa());
		model.addAttribute("observacoes", tecnico.getObservacoes());
		model.addAttribute("telefones", tecnico.getTelefone());
		model.addAttribute("dadosBancarios", tecnico.getDadosBancarios());
		model.addAttribute("endereco", tecnico.getEndereco());
		model.addAttribute("userName", tecnico.getUserName());
		model.addAttribute("password", tecnico.getPassword());
		int index = 0;
		for(Telefone t: tecnico.getTelefone()) {
			tecnico.getTelefone().get(index).setIdTecnico(tecnico);
			index++;
		}
		tecnico.setBloqueado(false);
		tecRepository.save(tecnico);
		red.addFlashAttribute("message", "Técnico salvo com sucesso!");
	   
		return "redirect:/cadastroTecnico";
	}
	@GetMapping("/listarTecnicos")
	public String listarTodos(Model model){
		model.addAttribute("tecnicos", tecRepository.findAll());
		return "/listarTecnico" ;
	}
  
	@GetMapping("editTecnico/{idTecnico}")
	public String editarTecnico(@PathVariable("idTecnico")Long id,Model model) {
		model.addAttribute("tecnico", tecRepository.findById(id).get());
		return "/editarTecnico";
	}
	@PutMapping("salvarEdicao/{idTecnico}")
	public String salvarEdicaoTecnico(@PathVariable("idTecnico")Long id, @ModelAttribute Tecnico tecnico,BindingResult result,Model model){
		
		System.out.println("ERROR: "+result);
		Optional<Tecnico> tecnicoProcurado = tecRepository.findById(id);
		if(tecnicoProcurado != null) {
			
			
			tecnicoProcurado.get().setBloqueado(tecnico.isBloqueado());
			tecnicoProcurado.get().setDadosBancarios(tecnico.getDadosBancarios());
			tecnicoProcurado.get().setDataNasc(tecnico.getDataNasc());
			tecnicoProcurado.get().setEmail(tecnico.getEmail());
			tecnicoProcurado.get().setEndereco(tecnico.getEndereco());
			tecnicoProcurado.get().setNome(tecnico.getNome());
			tecnicoProcurado.get().setNumDoc(tecnico.getNumDoc());
			tecnicoProcurado.get().setObservacoes(tecnico.getObservacoes());
			tecnicoProcurado.get().setPassword(tecnico.getPassword());
			tecnicoProcurado.get().setTelefone(tecnico.getTelefone());
			tecnicoProcurado.get().setTipoDoc(tecnico.getTipoDoc());
			tecnicoProcurado.get().setTipoPessoa(tecnico.getTipoPessoa());
			tecnicoProcurado.get().setUserName(tecnico.getUserName());
			
			tecRepository.save(tecnicoProcurado.get());
			
			return "redirect:/listarTecnicos";
		}
		
		return "redirect:/listarTecnicos";
	}
	
	@GetMapping("buscarTecnico/{idTecnico}")
	
	public String buscarTecnico(@PathVariable("idTecnico")Long id,Model model) {
		model.addAttribute("tecnico", tecRepository.findById(id).get());
		return "/bloquearTecnico";
	}
	
	
	@PutMapping("deletarTecnico/{idTecnico}")
	public String bloquearTecnico(@PathVariable("idTecnico")Long id,@ModelAttribute Tecnico tecnico,BindingResult result,Model model) {
		System.out.println("ERROR: "+result);
		Optional<Tecnico> tecnicoProcurado = tecRepository.findById(id);
		if(tecnicoProcurado != null) {
			if(tecnicoProcurado.get().isBloqueado()) {
				tecnicoProcurado.get().setBloqueado(false);
					
			}else {
			tecnicoProcurado.get().setBloqueado(true);
			}
			tecRepository.save(tecnicoProcurado.get());
			return "redirect:/listarTecnicos";
		}
		
		return "redirect:/listarTecnicos";
 
	}
	@GetMapping("/listarRatTecnico/{idTecnico}")
	public String listarRatTecnico(@PathVariable("idTecnico")Long id,@ModelAttribute Rat rat,BindingResult result,Model model) {
		
	
		model.addAttribute("rats",ratRep.findByTecnico(tecRepository.findById(id).get()));
		model.addAttribute("tecnico",tecRepository.findById(id).get());

		return "/listarRatPorTecnico";
	}
	
	
	
}
