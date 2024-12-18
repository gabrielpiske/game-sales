package com.venda.venda.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.venda.venda.model.Jogo;
import com.venda.venda.service.JogoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @PostMapping("/anunciar")
    public String salvarJogo(@ModelAttribute Jogo jogo) throws IOException {
        // TODO: process POST request
        if(jogo.getFile() != null){
            System.out.println("Aquivo: " + jogo.getFile().getOriginalFilename());
        }

        if (jogo.getFile() != null && !jogo.getFile().isEmpty()) {
            jogo.setImagem(jogo.getFile().getBytes());
        }
        jogoService.salvar(jogo);
        return "redirect:/home";
    }

}
