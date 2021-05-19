package com.projeto.api.controller;

import com.projeto.api.consulta.ConsultaFipe;
import com.projeto.api.consulta.FipeResponse;
import com.projeto.api.entity.Usuario;
import com.projeto.api.entity.Veiculo;
import com.projeto.api.repository.UsuarioRepository;
import com.projeto.api.repository.VeiculoRepository;
import com.projeto.api.request.UsuarioRequest;
import com.projeto.api.request.VeiculoRequest;
import com.projeto.api.response.ErroFormResponse;
import com.projeto.api.response.ErroHandler;
import com.projeto.api.response.VeiculoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/veiculos")
@Api(value="API Veiculos")
public class VeiculoController {

    private VeiculoRepository veiculoRepository;
    private UsuarioRepository usuarioRepository;
    private ErroHandler erroHandler;
    private ConsultaFipe consultaFipe;

    public VeiculoController(VeiculoRepository veiculoRepository,UsuarioRepository usuarioRepository,
                             ErroHandler erroHandle,ConsultaFipe consultaFipe) {
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.erroHandler = erroHandler;
        this.consultaFipe = consultaFipe;
    }


    @PostMapping
    @ApiOperation(value="Cadastra um veiculo para um usuário")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid VeiculoRequest request, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroHandler.GetAllErrors(result));
        }

        Optional<Usuario> usuario = usuarioRepository.findById(request.getUsuario());

        if(!usuario.isPresent()){
            return ResponseEntity.badRequest().build();
        }else{
            FipeResponse consulta = consultaFipe.consulta(request.getMarca(), request.getModelo(), request.getAno());

            Veiculo veiculo = request.converter(usuario.get(),consulta);
            veiculoRepository.save(veiculo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }



}
