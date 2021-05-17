package com.projeto.api.response;

import com.projeto.api.entity.Usuario;

import javax.persistence.*;

public class VeiculoResponse {

    private String marca;
    private String modelo;
    private Integer ano;
    private String valor;
    private Boolean rodizio;

    public VeiculoResponse( String marca, String modelo, Integer ano, String valor,Boolean rodizio) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.valor = valor;
        this.rodizio = rodizio;
    }

    public Boolean getRodizio() {
        return rodizio;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getValor() {
        return valor;
    }
}
