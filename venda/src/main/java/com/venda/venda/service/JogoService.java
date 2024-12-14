package com.venda.venda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venda.venda.model.Jogo;
import com.venda.venda.repository.JogoRepository;

@Service
public class JogoService {
    
    @Autowired
    private JogoRepository jogoRepository;

    public List<Jogo> listarTodos() {
        return jogoRepository.findAll();
    }

    public Jogo salvar(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    public Jogo buscarPorId(Integer id) {
        return jogoRepository.findById(id).orElse(null);
    }

    public void deletarPorId(Integer id) {
        jogoRepository.deleteById(id);
    }
}
