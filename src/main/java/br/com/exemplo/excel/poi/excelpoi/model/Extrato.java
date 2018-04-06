package br.com.exemplo.excel.poi.excelpoi.model;

public class Extrato {
	
	private Integer saldo;
	private String descricao;
	private String sinal;
	
	public Extrato(String descricao, String sinal, Integer saldo) {
		this.saldo = saldo;
		this.sinal = sinal;
		this.descricao = descricao;
	}
	
	public Integer getSaldo() {
		return saldo;
	}
	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSinal() {
		return sinal;
	}
	public void setSinal(String sinal) {
		this.sinal = sinal;
	}
}
