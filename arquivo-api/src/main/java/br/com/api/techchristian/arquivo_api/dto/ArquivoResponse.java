package br.com.api.techchristian.arquivo_api.dto;

import br.com.api.techchristian.arquivo_api.database.enums.ArquivoStatus;

import java.time.Instant;
import java.util.UUID;

public record ArquivoResponse(
        UUID id,
        String nomeOriginal,
        String contentType,
        long tamanhoBytes,
        ArquivoStatus status,
        String mensagemErro,
        Instant criadoEm,
        Instant atualizadoEm
) {
}
