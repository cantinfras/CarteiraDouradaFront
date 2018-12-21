package com.example.elton.carteiradourada.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TipoMulta {

    @SerializedName("id")
    @Expose
    private int id;

    private int pontos;
    private String codigo;
    private String descricao;
    private String infrator;

    public TipoMulta() {
    }

    public TipoMulta(int id, String codigo, String descricao, String infrator, int pontos) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.infrator = infrator;
        this.pontos = pontos;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInfrator() {
        return infrator;
    }

    public void setInfrator(String infrator) {
        this.infrator = infrator;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
