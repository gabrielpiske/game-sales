package com.venda.venda.controller;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.venda.venda.model.Jogo;
import com.venda.venda.model.Usuario;
import com.venda.venda.service.CarrinhoService;
import com.venda.venda.service.JogoService;
import com.venda.venda.service.UsuarioService;

@Controller
public class HomeController {

    @Autowired
    private JogoService jogoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/home")
    public String exibirHomePage() {
        System.out.println("Usuário autenticado: " + org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getName());
        return "home";
    }

    @GetMapping("/anunciar")
    public String exibirAnunciarPage(Model model) {
        model.addAttribute("jogo", new Jogo());
        model.addAttribute("jogos", jogoService.listarTodos());
        return "anunciar";
    }

    @GetMapping("/comprar")
    public String exibirComprarPage(Authentication authentication, Model model) {
        String email = authentication.getName();
        Optional<Usuario> usuario = usuarioService.buscarPorEmail(email);

        // adiciona nome ao usuario
        usuario.ifPresent(u -> model.addAttribute("usuarioNome", u.getNome()));

        // quantidade de itens no carrinho ao modelo
        Integer usuarioId = usuario.map(Usuario::getId_usuario).orElse(null);
        int quantidadeCarrinho = carrinhoService.quantidadeItensPorUsuario(usuarioId);
        model.addAttribute("quantidadeCarrinho", quantidadeCarrinho);

        List<Map<String, String>> jogosFormatados = jogoService.listarTodos().stream()
                .map(jogo -> Map.of(
                    "idJogo", String.valueOf(jogo.getIdJogo()),
                    "nome", jogo.getNome(),
                    "descricao", jogo.getDescricao(),
                    "imagem", Base64.getEncoder().encodeToString(jogo.getImagem()),
                    "preco", String.format("R$ %.2f", jogo.getPreco())
                ))
                .toList();
    
        model.addAttribute("jogos", jogosFormatados);

        return "comprar";
    }
}
