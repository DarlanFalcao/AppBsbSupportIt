package br.com.bsb.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name ="tb_rat")
public class Rat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idRat;
	@Column
	private String numOtrs;
	@Column
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataChamado;
	@Column
	private Date dataHoraInicio;
	@Column
	private Date dataHoraFim;
	@Column
	private String observacao;
	@Column
	private boolean equipFuncionando;
	@Column
	private String motivoChamado;
	@Column
	private String descricaoDoAtendimento;
	@ManyToOne()
	private Tecnico tecnico;
	@OneToOne
	private Cliente cliente;
	@OneToOne
	private TipoServico tipoServico;
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Endereco endereco;
	@OneToMany(mappedBy="id_material" )
	private List<Material> materiais;
	@Column
	private String status;
	@Column
	private boolean validado;
	@OneToOne
	private Funcionario funcValidou;
	@Column
	private double valor;
	
	public long getIdRat() {
		return idRat;
	}
	public void setIdRat(long idRat) {
		this.idRat = idRat;
	}
	public String getNumOtrs() {
		return numOtrs;
	}
	public void setNumOtrs(String numOtrs) {
		this.numOtrs = numOtrs;
	}
	public Date getDataChamado() {
		return dataChamado;
	}
	public void setDataChamado(Date dataChamado) {
		this.dataChamado = dataChamado;
	}
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	public Date getDataHoraFim() {
		return dataHoraFim;
	}
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public boolean isEquipFuncionando() {
		return equipFuncionando;
	}
	public void setEquipFuncionando(boolean equipFuncionando) {
		this.equipFuncionando = equipFuncionando;
	}
	public String getMotivoChamado() {
		return motivoChamado;
	}
	public void setMotivoChamado(String motivoChamado) {
		this.motivoChamado = motivoChamado;
	}
	public String getDescricaoDoAtendimento() {
		return descricaoDoAtendimento;
	}
	public void setDescricaoDoAtendimento(String descricaoDoAtendimento) {
		this.descricaoDoAtendimento = descricaoDoAtendimento;
	}
	public Tecnico getTecnico() {
		return tecnico;
	}
	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco  endereco) {
		this.endereco = endereco;
	}
	public List<Material> getMateriais() {
		return materiais;
	}
	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isValidado() {
		return validado;
	}
	public void setValidado(boolean validado) {
		this.validado = validado;
	}
	public Funcionario getFuncValidou() {
		return funcValidou;
	}
	public void setFuncValidou(Funcionario funcValidou) {
		this.funcValidou = funcValidou;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idRat ^ (idRat >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rat other = (Rat) obj;
		if (idRat != other.idRat)
			return false;
		return true;
	}
	
	
}
