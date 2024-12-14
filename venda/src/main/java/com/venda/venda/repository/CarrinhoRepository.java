package com.venda.venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venda.venda.model.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer>{
    
}
