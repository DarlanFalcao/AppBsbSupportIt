package br.com.bsb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_Conta")
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String nome;
	@Column
	private String descricao;
	@Column
	private double valor;
	@Column
	private boolean pago;
	@OneToOne
    private Funcionario funcValidou;
	
	
	public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getDescricao() {
	return descricao;
}
public void setDescricao(String descricao) {
	this.descricao = descricao;
}
public double getValor() {
	return valor;
}
public void setValor(double valor) {
	this.valor = valor;
}
public boolean isPago() {
	return pago;
}
public void setPago(boolean pago) {
	this.pago = pago;
}
public Funcionario getFuncValidou() {
	return funcValidou;
}
public void setFuncValidou(Funcionario funcValidou) {
	this.funcValidou = funcValidou;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (id ^ (id >>> 32));
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
	Conta other = (Conta) obj;
	if (id != other.id)
		return false;
	return true;
}

}
