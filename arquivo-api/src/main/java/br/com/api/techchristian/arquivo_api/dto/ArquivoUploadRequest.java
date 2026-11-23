package br.com.api.techchristian.arquivo_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record ArquivoUploadRequest(
        @NotNull
        MultipartFile file
) {}

