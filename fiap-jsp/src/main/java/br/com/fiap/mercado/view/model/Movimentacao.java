package br.com.fiap.mercado.view.model;
import java.util.Date;

public abstract class Movimentacao {
    private int cd_movimentacao, cd_extrato;
    private int qtd_parcelas;
    private double vl_movimentacao;
    private String nm_movimentacao;
    private String ds_movimentacao;
    private Date dt_movimentacao;

    public Movimentacao() {
    }

    public int get_cd_movimentacao() {
        return cd_movimentacao;
    }

    public void set_cd_movimentacao(int cd_movimentacao) {
        this.cd_movimentacao = cd_movimentacao;
    }
    
    public int get_cd_extrato() {
        return cd_extrato;
    }
    
    public void set_cd_extrato(int cd_extrato) {
        this.cd_extrato = cd_extrato;
    }

    public int get_qtd_parcelas() {
        return qtd_parcelas;
    }

    public void set_qtd_parcelas(int qtd_parcelas) {
        this.qtd_parcelas = qtd_parcelas;
    }

    public double get_vl_movimentacao() {
        return vl_movimentacao;
    }

    public void set_vl_movimentacao(double vl_movimentacao) {
        this.vl_movimentacao = vl_movimentacao;
    }

    public String get_nm_movimentacao() {
        return nm_movimentacao;
    }

    public void set_nm_movimentacao(String nm_movimentacao) {
        this.nm_movimentacao = nm_movimentacao;
    }

    public String get_ds_movimentacao() {
        return ds_movimentacao;
    }

    public void set_ds_movimentacao(String ds_movimentacao) {
        this.ds_movimentacao = ds_movimentacao;
    }

    public Date get_dt_movimentacao() {
        return dt_movimentacao;
    }

    public void set_dt_movimentacao(Date dt_movimentacao) {
        this.dt_movimentacao = dt_movimentacao;
    }

    // Métodos abstratos que subclasses devem implementar
    public abstract void realizarMovimentacao();
}