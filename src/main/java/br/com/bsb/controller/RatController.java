package br.com.bsb.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import br.com.bsb.model.Cliente;
import br.com.bsb.model.Endereco;
import br.com.bsb.model.Funcionario;
import br.com.bsb.model.Material;
import br.com.bsb.model.Rat;
import br.com.bsb.model.Tecnico;
import br.com.bsb.model.Telefone;
import br.com.bsb.model.TipoServico;
import br.com.bsb.repository.ClienteRepository;
import br.com.bsb.repository.FuncionarioRepository;
import br.com.bsb.repository.MaterialRepository;
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
@Autowired
FuncionarioRepository funcRep;
@Autowired
MaterialRepository matRep;
	
	@GetMapping("/cadastroRat")
	public String cadastrarNovaRat(Model model) {
	
		
		
		Rat rat = new Rat();
		Endereco end = new Endereco();
		Cliente clt = new Cliente();
		TipoServico tipoServ = new TipoServico();
		Funcionario func = new Funcionario();
		List<Material> material = new ArrayList<Material>();
		
		
		rat.setEndereco(end);
		rat.setCliente(clt);
		rat.setFuncValidou(func);
		rat.setMateriais(material);
		rat.setTipoServico(tipoServ);
		model.addAttribute("rat", rat);
		model.addAttribute("tecnicos", tecRep.findAll());
		model.addAttribute("tipoServicos", tipoRep.findAll());
		model.addAttribute("clientes", cltRep.findAll());
		model.addAttribute("funcionarios",funcRep.findAll());
		model.addAttribute("materiais",matRep.findAll());
		model.addAttribute("idTecnico", new Long(0));
		return "/cadastroRat";
	}
	
	@PostMapping("/salvarRat")
	public String newRat(@ModelAttribute("rat") Rat rat,
			BindingResult result,Model model) {
		
		System.out.println("ERRO: "+result);
		
		
		model.addAttribute("numOtrs", rat.getNumOtrs());
		model.addAttribute("dataChamado", rat.getDataChamado());
		model.addAttribute("dataHoraFim",rat.getDataHoraFim());
		model.addAttribute("observacao",rat.getObservacao());
		model.addAttribute("equipFuncionando",rat.isEquipFuncionando());
	    model.addAttribute("motivoChamado",rat.getMotivoChamado());
	    model.addAttribute("descricaoDoAtendimento",rat.getDescricaoDoAtendimento());
	    model.addAttribute("materiais",rat.getMateriais());
		model.addAttribute("validado",rat.isValidado());
		model.addAttribute("funcValidou",rat.getFuncValidou());
		model.addAttribute("valor",rat.getValor());
		Tecnico tecnico = new Tecnico();
		List<Telefone>telefones = new ArrayList<Telefone>();
		Telefone tel = new Telefone();
		telefones.add(tel);
		tecnico = tecRep.findById(rat.getTecnico().getIdTecnico()).get();
		tecnico.setTelefone(telefones);
		rat.setTecnico(tecnico);
		rat.setCliente(cltRep.findById(rat.getCliente().getId()).get());
		rat.setTipoServico(tipoRep.findById(rat.getTipoServico().getId()).get());
		model.addAttribute("tecnico",rat.getTecnico());
		model.addAttribute("cliente",rat.getCliente() );
		model.addAttribute("tipoServico",rat.getTipoServico() );
		model.addAttribute("endereco", rat.getEndereco());
		rat.setStatus("Aguardando Aprovação");
		model.addAttribute("status",rat.getStatus());
		
		ratRep.save(rat);
		
		return "redirect:/cadastroRat";
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
