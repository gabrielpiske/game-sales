package com.venda.venda.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import com.venda.venda.model.Jogo;
import com.venda.venda.service.CarrinhoService;
import com.venda.venda.service.JogoService;
import com.venda.venda.service.UsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/anunciar")
    public String salvarOuAtualizarJogo(@ModelAttribute Jogo jogo, @RequestParam("file") MultipartFile file)
            throws IOException {
        if (file != null && !file.isEmpty()) {
            jogo.setImagem(file.getBytes());
        }

        Jogo jogoExistente = null;
        if (jogo.getIdJogo() != null) {
            jogoExistente = jogoService.buscarPorId(jogo.getIdJogo());
        }

        if (jogoExistente != null) {
            jogoExistente.setNome(jogo.getNome());
            jogoExistente.setDescricao(jogo.getDescricao());
            jogoExistente.setDataLancamento(jogo.getDataLancamento());
            jogoExistente.setClassificacaoIndicativa(jogo.getClassificacaoIndicativa());
            jogoExistente.setPreco(jogo.getPreco());
            if (jogo.getImagem() != null) {
                jogoExistente.setImagem(jogo.getImagem());
            }
            jogoService.salvar(jogoExistente);
        } else {
            jogoService.salvar(jogo);
        }
        return "redirect:/anunciar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirJogo(@PathVariable("id") Integer id) {
        jogoService.deletarPorId(id);
        return "redirect:/anunciar";
    }

    @GetMapping("/editar/{id}")
    @ResponseBody
    public Jogo obterJogoParaEdicao(@PathVariable("id") Integer id) {
        return jogoService.buscarPorId(id);
    }

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
}
