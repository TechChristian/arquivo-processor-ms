package br.com.api.techchristian.arquivo_api.dto;

import br.com.api.techchristian.arquivo_api.database.enums.ArquivoStatus;

import java.util.UUID;

public record ArquivoUploadResponse(
        UUID id,
        ArquivoStatus status
) {
}
