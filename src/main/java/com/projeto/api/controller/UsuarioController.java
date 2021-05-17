package com.projeto.api.controller;


import com.projeto.api.entity.Usuario;
import com.projeto.api.entity.Veiculo;
import com.projeto.api.repository.UsuarioRepository;
import com.projeto.api.repository.VeiculoRepository;
import com.projeto.api.request.UsuarioRequest;
import com.projeto.api.response.VeiculoResponse;
import com.projeto.api.service.UsuarioService;
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
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioRepository usuarioRepository, VeiculoRepository veiculoRepository,
                             UsuarioService usuarioService){
        this.usuarioRepository = usuarioRepository;
        this.veiculoRepository = veiculoRepository;
        this.usuarioService = usuarioService;
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

        return ResponseEntity.ok(usuarioService.veiculo_list(veiculos));
    }



}
