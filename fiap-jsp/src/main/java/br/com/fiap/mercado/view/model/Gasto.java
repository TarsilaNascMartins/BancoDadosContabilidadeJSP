package br.com.fiap.mercado.view.model;

public class Gasto extends Movimentacao {
	private int cd_gasto;
	private String tp_despesa;

	public Gasto() {
	}

	public int get_cd_gasto() {
		return cd_gasto;
	}

	public void set_cd_gasto(int cd_gasto) {
		this.cd_gasto = cd_gasto;
	}

	public String get_tp_despesa() {
		return tp_despesa;
	}

	public void set_tp_despesa(String tp_despesa) {
		this.tp_despesa = tp_despesa;
	}

	@Override
	public void realizarMovimentacao() {
		// TODO Auto-generated method stub

	}

}