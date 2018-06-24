package br.com.bsb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="tb_Telefone")
public class Telefone {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long idTelefone;
	@Column
	private String tipo;
	@Column
	private String numero;
	
	@ManyToOne
	@JoinColumn(name ="tecnico_id")
	@JsonBackReference
	private Tecnico idTecnico;
	

	
	public Telefone() {
		// TODO Auto-generated constructor stub
	}



	public long getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(long idTelefone) {
		this.idTelefone = idTelefone;
	}

	public Tecnico getIdTecnico() {
		return idTecnico;
	}





	public void setIdTecnico(Tecnico tecnico) {
		this.idTecnico = tecnico;
	}





	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTecnico == null) ? 0 : idTecnico.hashCode());
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
		Telefone other = (Telefone) obj;
		if (idTecnico == null) {
			if (other.idTecnico != null)
				return false;
		} else if (!idTecnico.equals(other.idTecnico))
			return false;
		return true;
	}
	
	
	
	
	

}
