package com.projeto.api.controller;


import com.projeto.api.entity.Usuario;
import com.projeto.api.repository.UsuarioRepository;
import com.projeto.api.request.UsuarioRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest request){
        Usuario user = request.converter();
        usuarioRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
