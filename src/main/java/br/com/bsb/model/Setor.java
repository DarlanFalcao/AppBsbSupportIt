package br.com.bsb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_Setor")
public class Setor {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long idSetor;
	@Column
	private String nome;

	public long getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(long idSetor) {
		this.idSetor = idSetor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
