package com.projeto.api.entity;

import javax.persistence.*;

@Entity
@Table(name="veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private String ano;
    @ManyToOne @JoinColumn(name="usuario_id",nullable=false)
    private Usuario usuario;
    @Column(nullable = false)
    private String valor;

    public Veiculo(String marca, String modelo, String ano, Usuario usuario,String valor) {
        super();
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.usuario = usuario;
        this.valor = valor;
    }

    public Veiculo() {

    }

}
