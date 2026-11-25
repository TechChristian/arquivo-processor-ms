package br.com.api.techchristian.arquivo_api.dto;

import org.springframework.web.multipart.MultipartFile;

public record ArquivoUploadRequest(
        MultipartFile file
) {}

