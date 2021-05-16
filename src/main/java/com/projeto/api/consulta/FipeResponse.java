package com.projeto.api.consulta;

public class FipeResponse {

    private String Modelo;
    private String Valor;
    private String Marca;
    private Integer AnoModelo;
    private String Combustivel;
    private String CodigoFipe;
    private String MesReferencia;
    private Integer TipoVeiculo;
    private String SiglaCombustivel;


    public FipeResponse(String Modelo, String Valor, String Marca,
                        Integer AnoModelo, String Combustivel, String CodigoFipe,
                        String MesReferencia, Integer TipoVeiculo, String SiglaCombustivel) {
        this.Modelo = Modelo;
        this.Valor = Valor;
        this.Marca = Marca;
        this.AnoModelo = AnoModelo;
        this.Combustivel = Combustivel;
        this.CodigoFipe = CodigoFipe;
        this.MesReferencia = MesReferencia;
        this.TipoVeiculo = TipoVeiculo;
        this.SiglaCombustivel = SiglaCombustivel;
    }

    public String getModelo() {
        return Modelo;
    }

    public String getValor() {
        return Valor;
    }

    public String getMarca() {
        return Marca;
    }

    public Integer getAnoModelo() {
        return AnoModelo;
    }

    public String getCombustivel() {
        return Combustivel;
    }

    public String getCodigoFipe() {
        return CodigoFipe;
    }

    public String getMesReferencia() {
        return MesReferencia;
    }

    public Integer getTipoVeiculo() {
        return TipoVeiculo;
    }

    public String getSiglaCombustivel() {
        return SiglaCombustivel;
    }
}
