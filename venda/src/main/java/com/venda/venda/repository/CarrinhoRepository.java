package com.venda.venda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venda.venda.model.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer>{
    List<Carrinho> findByUsuarioId(Integer usuarioId);
}
