package com.venda.venda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.venda.venda.model.Jogo;

@Controller
public class HomeController {
    
    @GetMapping("/home")
    public String exibirHomePage() {
        System.out.println("Usuário autenticado: " + org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName());
        return "home";
    }

    @GetMapping("/anunciar")
    public String exibirAnunciarPage(Model model) {
        model.addAttribute("jogo", new Jogo());
        return "anunciar";
    }

    @GetMapping("/comprar")
    public String exibirComprarPage() {
        return "comprar";
    }
}
