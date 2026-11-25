package br.com.api.techchristian.arquivo_api.service;


import br.com.api.techchristian.arquivo_api.database.model.ArquivoEntity;
import br.com.api.techchristian.arquivo_api.database.repository.IArquivoRepository;
import br.com.api.techchristian.arquivo_api.dto.ArquivoUploadRequest;
import br.com.api.techchristian.arquivo_api.dto.ArquivoUploadResponse;
import br.com.api.techchristian.arquivo_api.exception.ArmazenamentoException;
import br.com.api.techchristian.arquivo_api.exception.ArquivoInvalidoException;
import br.com.api.techchristian.arquivo_api.mapper.ArquivoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArquivoService {
    private final IArquivoRepository arquivoRepository;

    @Value("${app.uploads-dir}")
    private String uploadsDir;

    public ArquivoUploadResponse upload(ArquivoUploadRequest request) {

        MultipartFile file = request.file();
        validar(file);

        log.info("Arquivo upload recebido:nome={}, tamanho={} bytes",
                file.getOriginalFilename(), file.getSize());

        Path destino = armazenar(file);

        try {
            ArquivoEntity arquivoRequestEntity = ArquivoMapper.toEntity(request, destino.toString());
            arquivoRepository.save(arquivoRequestEntity);

            return ArquivoMapper.toUploadResponse(arquivoRequestEntity);
        }catch (RuntimeException e){
            apagar(destino);
            throw e;
        }

    }

    private void validar(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            log.info("Arquivo vazio ou nullo.");
            throw new ArquivoInvalidoException("O arquivo está vazio");
        }
        if (!"application/pdf".equals(file.getContentType())) {
            throw new ArquivoInvalidoException("apenas arquivos PDF são permitidos.");
        }
    }

    private Path armazenar(MultipartFile file) {
        try {
            Path pasta = Path.of(uploadsDir);
            Files.createDirectories(pasta);

            Path destino = pasta.resolve(UUID.randomUUID() + ".pdf");
            file.transferTo(destino);
            return destino;
        }catch (IOException e){
            throw new ArmazenamentoException("Falha ao salvar o arquivo");
        }
    }

    private void apagar(Path caminho) {
        try {
            Files.deleteIfExists(caminho);
        }catch (IOException e) {
            log.warn("Falha ao deletar o arquivo {}",  caminho, e);
        }
    }

}

