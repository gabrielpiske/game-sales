package com.venda.venda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venda.venda.model.Carrinho;
import com.venda.venda.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
    
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public List<Carrinho> listarTodos() {
        return carrinhoRepository.findAll();
    }

    public Carrinho salvar(Carrinho carrinho) {
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho buscarPorId(Integer id) {
        return carrinhoRepository.findById(id).orElse(null);
    }

    public void deletarPorId(Integer id) {
        carrinhoRepository.deleteById(id);
    }
}
