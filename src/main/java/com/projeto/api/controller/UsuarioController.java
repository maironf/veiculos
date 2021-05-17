package com.projeto.api.controller;


import com.projeto.api.entity.Usuario;
import com.projeto.api.entity.Veiculo;
import com.projeto.api.repository.UsuarioRepository;
import com.projeto.api.repository.VeiculoRepository;
import com.projeto.api.request.UsuarioRequest;
import com.projeto.api.response.VeiculoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
    private VeiculoRepository veiculoRepository;

    public UsuarioController(UsuarioRepository usuarioRepository, VeiculoRepository veiculoRepository){
        this.usuarioRepository = usuarioRepository;
        this.veiculoRepository = veiculoRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest request){
        Usuario user = request.converter();
        usuarioRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/veiculos")
    public ResponseEntity<?> getusuario(@PathVariable(name="id",required = true) Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(!usuario.isPresent()){
            return ResponseEntity.notFound().build();
        }
        List<Veiculo> veiculos = veiculoRepository.findAllByUsuario(usuario.get());

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

        return ResponseEntity.ok(veiculoResponse);
    }


}
