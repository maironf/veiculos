package com.projeto.api.service;

import com.projeto.api.entity.Veiculo;
import com.projeto.api.response.VeiculoResponse;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    public List<VeiculoResponse> veiculo_list(List<Veiculo> veiculos){
        Date hoje = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoje);
        int valor_hoje = cal.get(Calendar.DAY_OF_WEEK);

        List<VeiculoResponse> veiculoResponse = veiculos.stream()
                .map(veiculo ->{
                    Boolean rodizio = false;
                    Character ultimodigito = veiculo.getAno().toString().charAt(veiculo.getAno().toString().length()-1);

                    if(valor_hoje == 2 && (ultimodigito == '0' || ultimodigito == '1') ){
                        rodizio = true;
                    }else if(valor_hoje == 3 && (ultimodigito == '2' || ultimodigito == '3')){
                        rodizio = true;
                    }else if(valor_hoje == 4 && (ultimodigito == '4' || ultimodigito == '5')){
                        rodizio = true;
                    }else if(valor_hoje == 5 && (ultimodigito == '6' || ultimodigito == '7')){
                        rodizio = true;
                    }else if(valor_hoje == 6 && (ultimodigito == '8' || ultimodigito == '9')){
                        rodizio = true;
                    }

                    return new VeiculoResponse(veiculo.getMarca(),veiculo.getModelo(),veiculo.getAno(),
                            veiculo.getValor(),rodizio);


                }).collect(Collectors.toList());

        return veiculoResponse;

    }
}
