package br.com.bsb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_dadosBancario")
public class DadosBancarios {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long idConta;
	@Column
	private String agencia;
	@Column
	private String conta;
	@Column
	private String operacao;
	@Column
	private String tipoConta;
	
	@Column
	private String banco;
	
	
	
	
	
	
	
	public DadosBancarios() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	public long getIdConta() {
		return idConta;
	}





	public void setIdConta(long idConta) {
		this.idConta = idConta;
	}





	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}





	public String getTipoConta() {
		return tipoConta;
	}





	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}





	public String getBanco() {
		return banco;
	}





	public void setBanco(String banco) {
		this.banco = banco;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idConta ^ (idConta >>> 32));
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
		DadosBancarios other = (DadosBancarios) obj;
		if (idConta != other.idConta)
			return false;
		return true;
	}
	
	
	
	
	
	
}
