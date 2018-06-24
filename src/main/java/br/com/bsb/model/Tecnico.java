package br.com.bsb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="tb_Tecnico")
public class Tecnico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long idTecnico;
	@Column
	private String nome;
	@Column
	private String email;
	@Column
	private String tipoDoc;
	@Column
	private String numDoc;
	@Column
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataNasc;
	@Column
	private String tipoPessoa;
	@Column
	private String observacoes;
	
	@Column
	private boolean bloqueado;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	private transient String dataTemp;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="idTecnico", fetch = FetchType.EAGER)
	private List<Telefone> telefone;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DadosBancarios dadosBancarios;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private Endereco endereco;
	
	
	
	
	public Tecnico() {
		// TODO Auto-generated constructor stub
	}


	

	public long getIdTecnico() {
		return idTecnico;
	}




	public void setIdTecnico(long idTecnico) {
		this.idTecnico = idTecnico;
	}




	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTipoDoc() {
		return tipoDoc;
	}



	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}



	public String getNumDoc() {
		return numDoc;
	}



	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}



	public Date getDataNasc() {
		return dataNasc;
	}



	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}



	public String getTipoPessoa() {
		return tipoPessoa;
	}



	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}



	public String getObservacoes() {
		return observacoes;
	}



	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}




	public List<Telefone> getTelefone() {
		return telefone;
	}




	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}




	public DadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}




	public void setDadosBancarios(DadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}




	public Endereco getEndereco() {
		return endereco;
	}




	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}




	public boolean isBloqueado() {
		return bloqueado;
	}




	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}




	public String getDataTemp() {
		return dataTemp;
	}




	public void setDataTemp(String dataTemp) {
		this.dataTemp = dataTemp;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idTecnico ^ (idTecnico >>> 32));
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
		Tecnico other = (Tecnico) obj;
		if (idTecnico != other.idTecnico)
			return false;
		return true;
	}
	
	
	
	
	

}
