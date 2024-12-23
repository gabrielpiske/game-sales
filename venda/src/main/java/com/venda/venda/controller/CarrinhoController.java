package com.venda.venda.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.venda.venda.model.Jogo;
import com.venda.venda.model.Usuario;
import com.venda.venda.service.CarrinhoService;
import com.venda.venda.service.JogoService;
import com.venda.venda.service.UsuarioService;

@Controller
public class CarrinhoController {

    @Autowired
    private JogoService jogoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/jogos")
    @ResponseBody
    public List<Jogo> listarJogos() {
        return jogoService.listarTodos();
    }

    @PostMapping("/carrinho/adicionar")
    @ResponseBody
    public String adicionarAoCarrinho(@RequestParam Integer jogoId, Authentication authentication) {
        String email = authentication.getName();
        Integer usuarioId = usuarioService.buscarIdPorEmail(email);

        if (usuarioId == null) {
            return "Erro: Usuário não encontrado!";
        }

        try {
            carrinhoService.adicionarJogo(usuarioId, jogoId);
            return "Jogo adicionado ao carrinho!";
        } catch (Exception e) {
            return "Erro ao adicionar jogo ao carrinho: " + e.getMessage();
        }
    }


    @GetMapping("/carrinho/quantidade")
    @ResponseBody
    public Map<String, Integer> quantidadeCarrinho(Authentication authentication) {
        String email = authentication.getName();
        Integer usuarioId = usuarioService.buscarIdPorEmail(email);
        int quantidade = carrinhoService.quantidadeItensPorUsuario(usuarioId);

        return Map.of("quantidade", quantidade);
    }

    @GetMapping("/carrinho")
    public String exibirCarrinhoPage() {
        return "carrinho";
    }
    
}
