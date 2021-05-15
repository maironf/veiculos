package com.projeto.api.response;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
@Component
public class ErroHandler {

    public List<ErroFormResponse> GetAllErrors(BindingResult result){
        List<FieldError> erros=result.getFieldErrors();
        List<ErroFormResponse> listadeerro = new ArrayList<>();
        erros.forEach(erro->{
            String mensagem = erro.getDefaultMessage();
            ErroFormResponse e = new ErroFormResponse(erro.getField(),mensagem);
            listadeerro.add(e);
        });
        return listadeerro;
    }

}
