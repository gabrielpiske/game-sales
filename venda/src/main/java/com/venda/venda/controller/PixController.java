package com.venda.venda.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venda.venda.service.CarrinhoService;
import com.venda.venda.service.PixService;
import com.venda.venda.service.UsuarioService;

@Controller
public class PixController {

    @Autowired
    private PixService pixService;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/pagar-com-pix")
    public String exibirPixPage(Authentication authentication, Model model) throws UnsupportedEncodingException {
        String email = authentication.getName();
        Integer usuarioId = usuarioService.buscarIdPorEmail(email);
        Double valorTotal = carrinhoService.calcularValorTotalPorUsuario(usuarioId);

        //Chave PIX de teste do Banco Central (sandbox)
        String chavePix = "596032853567197-121316-7bc1ad301bc62c1b7c19f16adf5dd312";
        String descricao = "Pagamento de Jogos";
        String cidade = "São Paulo";
        String idTransacao = "TRANS123456";

        String payloadPix = pixService.gerarPayloadPix(chavePix, descricao, valorTotal, cidade, idTransacao);

        // Codificando o payload
        String encodedPayload = URLEncoder.encode(payloadPix, "UTF-8");

        model.addAttribute("qrCodePayload", encodedPayload);

        return "pixPage";
    }

    @GetMapping("/gerar-qrcode")
    public ResponseEntity<byte[]> gerarQRCode(@RequestParam String payload) throws Exception {
        byte[] qrCode = pixService.gerarQRCode(payload);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"qrcode.png\"")
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCode);
    }
}
