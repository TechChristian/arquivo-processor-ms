package br.com.api.techchristian.arquivo_api.dto;

import br.com.api.techchristian.arquivo_api.database.enums.ArquivoStatus;

import java.time.Instant;
import java.util.UUID;

public record ArquivoDetalhesResponse(
        UUID id,
        String nomeOriginal,
        String contentType,
        long tamanhoBytes,
        ArquivoStatus status,
        String textoExtraido,
        String mensagemErro,
        Instant criadoEm,
        Instant atualizadoEm
) {
}
