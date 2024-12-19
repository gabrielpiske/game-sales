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
        if (jogo.getIdJogo() != null && jogoRepository.existsById(jogo.getIdJogo())) {
            Jogo jogoExistente = jogoRepository.findById(jogo.getIdJogo()).orElse(null);
            if (jogoExistente != null) {
                jogoExistente.setNome(jogo.getNome());
                jogoExistente.setDescricao(jogo.getDescricao());
                jogoExistente.setDataLancamento(jogo.getDataLancamento());
                jogoExistente.setClassificacaoIndicativa(jogo.getClassificacaoIndicativa());
                jogoExistente.setPreco(jogo.getPreco());
                if (jogo.getImagem() != null) {
                    jogoExistente.setImagem(jogo.getImagem());
                }
                return jogoRepository.save(jogoExistente);
            }
        }
        return jogoRepository.save(jogo);
    }

    public Jogo buscarPorId(Integer id) {
        return jogoRepository.findById(id).orElse(null);
    }

    public void deletarPorId(Integer id) {
        jogoRepository.deleteById(id);
    }
}
