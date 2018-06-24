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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idSetor ^ (idSetor >>> 32));
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
		Setor other = (Setor) obj;
		if (idSetor != other.idSetor)
			return false;
		return true;
	}

}
