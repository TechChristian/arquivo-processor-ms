package br.com.api.techchristian.arquivo_api.controller;

import br.com.api.techchristian.arquivo_api.dto.ArquivoDetalhesResponse;
import br.com.api.techchristian.arquivo_api.dto.ArquivoUploadRequest;
import br.com.api.techchristian.arquivo_api.dto.ArquivoUploadResponse;
import br.com.api.techchristian.arquivo_api.service.ArquivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/arquivos")
@RequiredArgsConstructor
public class ArquivoController {

    private final ArquivoService arquivoService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ArquivoUploadResponse> upload(@Valid @ModelAttribute ArquivoUploadRequest arquivoUploadRequest) {

        ArquivoUploadResponse response = arquivoService.upload(arquivoUploadRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArquivoDetalhesResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(arquivoService.buscarArquivoId(id));

    }
}
