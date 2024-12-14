package com.venda.venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venda.venda.model.Jogo;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Integer>{
    
}
