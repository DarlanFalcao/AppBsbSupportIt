package br.com.bsb.controller;

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

import br.com.bsb.model.Endereco;
import br.com.bsb.model.Rat;
import br.com.bsb.model.Tecnico;
import br.com.bsb.model.Telefone;
import br.com.bsb.repository.ClienteRepository;
import br.com.bsb.repository.RatRepository;
import br.com.bsb.repository.TecnicoRepository;
import br.com.bsb.repository.TipoServicoRepository;

@Controller
@RequestMapping("/")
public class RatController {
@Autowired
RatRepository ratRep;
@Autowired
TecnicoRepository tecRep;
@Autowired
TipoServicoRepository tipoRep;
@Autowired
ClienteRepository cltRep;
	
	@GetMapping("/cadastroRat")
	public String cadastrarNovaRat(Model model) {
	
		Rat rat = new Rat();
		Endereco end = new Endereco();
		rat.setEndereco(end);
		model.addAttribute("rat", rat);
		model.addAttribute("tecnicos", tecRep.findAll());
		model.addAttribute("tipoServicos", tipoRep.findAll());
		model.addAttribute("clientes", cltRep.findAll());
		return "/cadastroRat";
	}
	
	@PostMapping("/salvarRat")
	public String newRat(@ModelAttribute("rat") Rat rat,
			RedirectAttributes red,BindingResult result, Model model) {
		
		System.out.println("ERRO: "+result);
		model.addAttribute("numOtrs", rat.getNumOtrs());
		model.addAttribute("dataChamado", rat.getDataChamado());
		model.addAttribute("tecnico", rat.getTecnico());
		model.addAttribute("cliente", rat.getCliente());
		model.addAttribute("tipoServico", rat.getTipoServico());
		model.addAttribute("endereco", rat.getEndereco());
		rat.setStatus("Aguardando Aprovação");
		ratRep.save(rat);
		
		return "redirect:/listarRats";
	}
	@GetMapping("/listarRats")
	public String listarRats(Model model){
		model.addAttribute("rats", ratRep.findAll());
		return "/listarRat" ;
	}
  
	@GetMapping("editarRat/{idRat}")
	public String editarTecnico(@PathVariable("idRat")Long id,Model model) {
		model.addAttribute("rat", ratRep.findById(id).get());
		return "/editarRat";
	}
	@PutMapping("salvarEdicaoRat/{idRat}")
	public String salvarEdicaoTecnico(@PathVariable("idRat")Long id, @ModelAttribute Rat rat,BindingResult result,Model model){
		
		System.out.println("ERROR: "+result);
		Rat ratEscolhido = ratRep.findById(id).get();
		if(ratEscolhido != null) {
			
			
			ratEscolhido.setCliente(rat.getCliente());
			ratEscolhido.setDataChamado(rat.getDataChamado());
			ratEscolhido.setDataHoraFim(rat.getDataHoraFim());
			ratEscolhido.setDataHoraInicio(rat.getDataHoraInicio());
			ratEscolhido.setDescricaoDoAtendimento(rat.getDescricaoDoAtendimento());
			ratEscolhido.setEndereco(rat.getEndereco());
			ratEscolhido.setEquipFuncionando(rat.isEquipFuncionando());
			ratEscolhido.setFuncValidou(rat.getFuncValidou());
			ratEscolhido.setMateriais(rat.getMateriais());
			ratEscolhido.setMotivoChamado(rat.getMotivoChamado());
			ratEscolhido.setNumOtrs(rat.getNumOtrs());
			ratEscolhido.setObservacao(rat.getObservacao());
			ratEscolhido.setStatus("Aguardando Aprovação");
			ratEscolhido.setTecnico(rat.getTecnico());
			ratEscolhido.setTipoServico(rat.getTipoServico());
			ratEscolhido.setValidado(rat.isValidado());
			ratEscolhido.setValor(rat.getValor());
			
			ratRep.save(ratEscolhido);
			
			return "redirect:/listarRats";
		}
		
		return "redirect:/listarRats";
	}
	
	
	
	@PutMapping("deletarRat/{idRat}")
	public String bloquearTecnico(@PathVariable("idRat")Long id,@ModelAttribute Rat rat,BindingResult result,Model model) {
		System.out.println("ERROR: "+result);
		Rat ratEscolhido = ratRep.findById(id).get();
		if(ratEscolhido != null) {
			
			
			ratRep.save(ratEscolhido);
			return "redirect:/listarRats";
		}
		
		return "redirect:/listarRats";
 
	}
	
}
