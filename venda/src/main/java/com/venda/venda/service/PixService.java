package com.venda.venda.service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class PixService {
    
    public String gerarPayloadPix(String chavePix, String descricao, double valor, String cidade, String idTransacao) {
        return "000201" +
                "010212" +
                "26" + String.format("%02d", chavePix.length() + 4) + "0014BR.GOV.BCB.PIX01" + chavePix +
                "52040000" +
                "5303986" +
                "54" + String.format("%02d", String.format("%.2f", valor).length()) + String.format("%.2f", valor) +
                "5802BR" +
                "59" + String.format("%02d", descricao.length()) + descricao +
                "60" + String.format("%02d", cidade.length()) + cidade +
                "62" + String.format("%02d", idTransacao.length() + 4) + "05" + idTransacao +
                "6304"; // CRC16 será calculado automaticamente.
    }

    public byte[] gerarQRCode(String payload) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = qrCodeWriter.encode(payload, BarcodeFormat.QR_CODE, 300, 300, hints);

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar QR Code", e);
        }
    }
}
