package br.com.fiap.mercado.view.model;


public class Ganho extends Movimentacao {
    private int cd_ganho;
    private String tp_receita;
  
    public Ganho() {
    }
    
    public int get_cd_ganho() {
        return cd_ganho;
    }

    public void set_cd_ganho(int cd_ganho) {
        this.cd_ganho = cd_ganho;
    }

    public String get_tp_receita() {
        return tp_receita;
    }

    public void set_tp_receita(String tp_receita) {
        this.tp_receita = tp_receita;
    }

	@Override
	public void realizarMovimentacao() {
		// TODO Auto-generated method stub
		
	}
    
}