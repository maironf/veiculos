package com.projeto.api.consulta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ConsultaFipe",url="https://parallelum.com.br/fipe/api/v1/carros/marcas")
public interface ConsultaFipe {

    @GetMapping("/{marca}/modelos/{modelo}/anos/{ano}")
    FipeResponse consulta(@PathVariable("marca") String marca,@PathVariable("modelo") String modelo,
                          @PathVariable("ano") String ano);



}
