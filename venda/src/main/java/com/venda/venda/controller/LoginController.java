package com.venda.venda.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venda.venda.model.Usuario;
import com.venda.venda.service.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String exibirLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String autenticarUsuario(@RequestParam String email,
            @RequestParam String senha,
            Model model) {
        // TODO: process POST request
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorEmail(email);


        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(senha)) {
            model.addAttribute("usuario", usuarioOpt.get());
            return "redirect:/home";
        }

        model.addAttribute("erro", "Credenciais inválidas. Tente novamente");
        return "login";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(Usuario usuario, Model model) {
        // TODO: process POST request
        usuarioService.salvar(usuario);
        model.addAttribute("mensagem", "Cadastro realizado com sucesso!");

        return "redirect:/login";
    }
}
