package br.com.overflow.intermediario.persistenciaEquipamento;

import java.math.BigDecimal;

public class Equipamento {
	
	private String nome;
	private BigDecimal valor;
	private BigDecimal idEquipamento;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getIdEquipamento() {
		return idEquipamento;
	}
	public void setIdEquipamento(BigDecimal idEquipamento) {
		this.idEquipamento = idEquipamento;
	}
	

}
