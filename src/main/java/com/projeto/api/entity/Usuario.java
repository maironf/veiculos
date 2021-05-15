package com.projeto.api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false,unique = true)
    private String cpf;
    @Column(columnDefinition = "DATE",nullable = false)
    private Date data_nascimento;

    public Usuario(String email, String senha, String cpf, Date data_nascimento) {
        super();
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
    }

    public Usuario() {

    }
}
