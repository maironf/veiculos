package com.projeto.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.projeto.api.entity.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UsuarioRequest {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min=6)
    private String senha;
    @NotBlank
    private String cpf;
    @NotNull
    @JsonFormat(pattern="dd/MM/yyyy",shape = JsonFormat.Shape.STRING)
    private Date data_nascimento;

    public UsuarioRequest(@Email @NotBlank String email, @NotBlank @Size(min=6) String senha,
                          @NotBlank String cpf) {
        super();
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Usuario converter(){
        return new Usuario(email,senha,cpf,data_nascimento);
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
