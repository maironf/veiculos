package com.projeto.api.request;

import com.projeto.api.consulta.FipeResponse;
import com.projeto.api.entity.Usuario;
import com.projeto.api.entity.Veiculo;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VeiculoRequest {

    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotBlank
    private String ano;
    @NotNull
    private Long usuario;


    public VeiculoRequest(@NotBlank String marca, @NotBlank String modelo, @NotBlank String ano,
                          @NotNull Long usuario) {
        super();
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.usuario = usuario;
    }

    public Veiculo converter(Usuario user, FipeResponse consulta){
        return new Veiculo(consulta.getMarca(), consulta.getModelo(), consulta.getAnoModelo(),user, consulta.getValor());
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAno() {
        return ano;
    }

    public Long getUsuario() {
        return usuario;
    }
}
