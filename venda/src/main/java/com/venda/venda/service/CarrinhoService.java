package com.venda.venda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venda.venda.model.Carrinho;
import com.venda.venda.model.Jogo;
import com.venda.venda.model.Usuario;
import com.venda.venda.repository.CarrinhoRepository;
import com.venda.venda.repository.JogoRepository;
import com.venda.venda.repository.UsuarioRepository;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private JogoRepository jogoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void adicionarJogo(Integer usuarioId, Integer jogoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Jogo jogo = jogoRepository.findById(jogoId)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado!"));

        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        carrinho.setJogo(jogo);
        carrinho.setValorTotal(jogo.getPreco());

        carrinhoRepository.save(carrinho);
    }

    public Double calcularValorTotalPorUsuario(Integer usuarioId) {
        return carrinhoRepository
            .findByUsuarioId(usuarioId)
            .stream()
            .mapToDouble(Carrinho::getValorTotal)
            .sum();
    }

    public void deletarItemDoCarrinho(Integer carrinhoId) {
        carrinhoRepository.deleteById(carrinhoId);
    }

    public int quantidadeItensPorUsuario(Integer usuarioId) {
        return carrinhoRepository.findByUsuarioId(usuarioId).size();
    }
}
