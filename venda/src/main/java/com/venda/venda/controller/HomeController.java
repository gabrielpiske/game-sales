package com.venda.venda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.venda.venda.model.Jogo;
import com.venda.venda.service.JogoService;

@Controller
public class HomeController {

    @Autowired
    private JogoService jogoService;
    
    @GetMapping("/home")
    public String exibirHomePage() {
        System.out.println("Usuário autenticado: " + org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName());
        return "home";
    }

    @GetMapping("/anunciar")
    public String exibirAnunciarPage(Model model) {
        model.addAttribute("jogo", new Jogo());
        model.addAttribute("jogos", jogoService.listarTodos());
        return "anunciar";
    }

    @GetMapping("/comprar")
    public String exibirComprarPage() {
        return "comprar";
    }
}
