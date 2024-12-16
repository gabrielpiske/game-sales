package com.venda.venda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.venda.venda.model.Usuario;
import com.venda.venda.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class UsuarioController {
    
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
        //TODO: process POST request
        Usuario usuario = usuarioService.buscarPorEmail(email);
        
        if(usuario != null && usuario.getSenha().equals(senha)){
            model.addAttribute("usuario", usuario);
            return "redirect:/home";
        }

        model.addAttribute("erro", "Credenciais inválidas . Tente novamente");
        return "login";
    }
    
    @GetMapping("/cadastro")
    public String exibirCadastroPage() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(Usuario usuario, Model model) {
        //TODO: process POST request
        usuarioService.salvar(usuario);
        model.addAttribute("mensagem", "Cadastro realizado com sucesso!");
        
        return "redirect:/login";
    }
    
    
}
