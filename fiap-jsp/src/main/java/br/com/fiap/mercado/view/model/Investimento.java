package br.com.fiap.mercado.view.model;

import java.util.Date;

public class Investimento {
    private int cd_investimento;
    private String ds_investimento;
    private double vl_investimento;
    private Date dt_investimento;
    private int cd_usuario;

    // Construtor vazio
    public Investimento() {
    }

    // Construtor com parâmetros
    public Investimento(int cd_investimento, String ds_investimento, double vl_investimento, Date dt_investimento, int cd_usuario) {
        this.cd_investimento = cd_investimento;
        this.ds_investimento = ds_investimento;
        this.vl_investimento = vl_investimento;
        this.dt_investimento = dt_investimento;
        this.cd_usuario = cd_usuario;
    }

    // GET e SET para cd_investimento
    public int get_cd_investimento() {
        return cd_investimento;
    }

    public void set_cd_investimento(int cd_investimento) {
        this.cd_investimento = cd_investimento;
    }

    // GET e SET para ds_investimento
    public String get_ds_investimento() {
        return ds_investimento;
    }

    public void set_ds_investimento(String ds_investimento) {
        this.ds_investimento = ds_investimento;
    }

    // GET e SET para vl_investimento
    public double get_vl_investimento() {
        return vl_investimento;
    }

    public void set_vl_investimento(double vl_investimento) {
        this.vl_investimento = vl_investimento;
    }

    // GET e SET para dt_investimento
    public Date get_dt_investimento() {
        return dt_investimento;
    }

    public void set_dt_investimento(Date dt_investimento) {
        this.dt_investimento = dt_investimento;
    }

    // GET e SET para cd_usuario
    public int get_cd_usuario() {
        return cd_usuario;
    }

    public void set_cd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }
}
