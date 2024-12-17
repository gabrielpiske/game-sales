package com.venda.venda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/home")
    public String exibirHomePage() {
        System.out.println("Usuário autenticado: " + org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName());
        return "home";
    }

    @GetMapping("/anunciar")
    public String exibirAnunciarPage() {
        return "anunciar";
    }

    @GetMapping("/comprar")
    public String exibirComprarPage() {
        return "comprar";
    }
}
