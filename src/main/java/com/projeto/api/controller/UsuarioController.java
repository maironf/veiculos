package com.projeto.api.controller;


import com.projeto.api.entity.Usuario;
import com.projeto.api.entity.Veiculo;
import com.projeto.api.repository.UsuarioRepository;
import com.projeto.api.repository.VeiculoRepository;
import com.projeto.api.request.UsuarioRequest;
import com.projeto.api.response.ErroFormResponse;
import com.projeto.api.response.ErroHandler;
import com.projeto.api.response.VeiculoResponse;
import com.projeto.api.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@Api(value="API Usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;
    private VeiculoRepository veiculoRepository;
    private UsuarioService usuarioService;
    private ErroHandler erroHandler;

    public UsuarioController(UsuarioRepository usuarioRepository, VeiculoRepository veiculoRepository,
                             UsuarioService usuarioService, ErroHandler erroHandler){
        this.usuarioRepository = usuarioRepository;
        this.veiculoRepository = veiculoRepository;
        this.usuarioService = usuarioService;
        this.erroHandler = erroHandler;
    }

    @PostMapping
    @ApiOperation(value="Cadastra um usuário")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest request, BindingResult result){

        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroHandler.GetAllErrors(result));
        }

        Optional<Usuario> usuario = usuarioRepository.findByEmail(request.getEmail());

        if(usuario.isPresent()){
            return ResponseEntity.badRequest().build();
        }

        Usuario user = request.converter();
        usuarioRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/veiculos")
    @ApiOperation(value="Retorna os veiculos de um usuário")
    public ResponseEntity<?> getusuario(@PathVariable(name="id",required = true) Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(!usuario.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroFormResponse("usuario","usuario não encontrado :("));
        }
        List<Veiculo> veiculos = veiculoRepository.findAllByUsuario(usuario.get());

        return ResponseEntity.ok(usuarioService.veiculo_list(veiculos));
    }



}
