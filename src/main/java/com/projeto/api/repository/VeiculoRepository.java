package com.projeto.api.repository;

import com.projeto.api.entity.Usuario;
import com.projeto.api.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {

    List<Veiculo> findAllByUsuario(Usuario usuario);
}
