package com.projeto.api.response;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ErroFormResponse {

    private String campo;
    private String erro;

    public ErroFormResponse(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }


    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
