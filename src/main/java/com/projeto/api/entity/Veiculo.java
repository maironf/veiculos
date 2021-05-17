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
    private Integer ano;
    @ManyToOne @JoinColumn(name="usuario_id",nullable=false)
    private Usuario usuario;
    @Column(nullable = false)
    private String valor;

    public Veiculo(String marca, String modelo, Integer ano, Usuario usuario,String valor) {
        super();
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.usuario = usuario;
        this.valor = valor;
    }

    public Veiculo() {

    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getValor() {
        return valor;
    }
}
