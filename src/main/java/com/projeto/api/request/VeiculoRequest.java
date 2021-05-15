package com.projeto.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.usuario = usuario;
    }

    public Veiculo converter(Usuario user){
        return new Veiculo(marca,modelo,ano,user);
    }

    public Long getUsuario() {
        return usuario;
    }
}
