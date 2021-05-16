package com.projeto.api.controller;

import com.projeto.api.entity.Usuario;
import com.projeto.api.entity.Veiculo;
import com.projeto.api.repository.UsuarioRepository;
import com.projeto.api.repository.VeiculoRepository;
import com.projeto.api.request.VeiculoRequest;
import com.projeto.api.response.ErroFormResponse;
import com.projeto.api.response.ErroHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private VeiculoRepository veiculoRepository;
    private UsuarioRepository usuarioRepository;
    private ErroHandler erroHandler;

    public VeiculoController(VeiculoRepository veiculoRepository,UsuarioRepository usuarioRepository,
                             ErroHandler erroHandler) {
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.erroHandler = erroHandler;
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid VeiculoRequest request, BindingResult result){
        System.out.println(result.hasErrors());

        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroHandler.GetAllErrors(result));
        }

        Optional<Usuario> usuario = usuarioRepository.findById(request.getUsuario());

        if(!usuario.isPresent()){
            return ResponseEntity.badRequest().build();
        }else{
            Veiculo veiculo = request.converter(usuario.get());
            veiculoRepository.save(veiculo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }



}
