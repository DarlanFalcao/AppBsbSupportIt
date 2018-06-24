package br.com.bsb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_material")
public class Material {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id_material;
	@Column
	private String nome;
	@Column
	private double valorDeCompra;
	@Column
	private double valorDeRevenda;
	
	@Column
	private String tipoUn;
	
	
	
	public long getId() {
		return id_material;
	}
	public void setId(long id_material) {
		this.id_material = id_material;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValorDeCompra() {
		return valorDeCompra;
	}
	public void setValorDeCompra(double valor) {
		this.valorDeCompra = valor;
	}
	public String getTipoUn() {
		return tipoUn;
	}
	public void setTipoUn(String tipoUn) {
		this.tipoUn = tipoUn;
	}
	public double getValorDeRevenda() {
		return valorDeRevenda;
	}
	public void setValorDeRevenda(double valorDeRevenda) {
		this.valorDeRevenda = valorDeRevenda;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id_material ^ (id_material >>> 32));
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
		Material other = (Material) obj;
		if (id_material != other.id_material)
			return false;
		return true;
	}
	
	

}
