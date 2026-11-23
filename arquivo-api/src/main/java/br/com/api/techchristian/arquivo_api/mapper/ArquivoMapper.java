package br.com.api.techchristian.arquivo_api.mapper;

import br.com.api.techchristian.arquivo_api.database.enums.ArquivoStatus;
import br.com.api.techchristian.arquivo_api.database.model.ArquivoEntity;
import br.com.api.techchristian.arquivo_api.dto.ArquivoDetalhesResponse;
import br.com.api.techchristian.arquivo_api.dto.ArquivoResponse;
import br.com.api.techchristian.arquivo_api.dto.ArquivoUploadRequest;
import br.com.api.techchristian.arquivo_api.dto.ArquivoUploadResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ArquivoMapper {
    // DTO -> entity
    public static ArquivoEntity toEntity (ArquivoUploadRequest request, String caminho){
        MultipartFile file = request.file();
        ArquivoEntity arquivo = new ArquivoEntity();

        arquivo.setNomeOriginal(file.getOriginalFilename());
        arquivo.setCaminho(caminho);
        arquivo.setContentType(file.getContentType());
        arquivo.setTamanhoBytes(file.getSize());
        arquivo.setArquivoStatus(ArquivoStatus.PENDENTE);
        return arquivo;
    }

    //entity -> DTO
    public static ArquivoUploadResponse toUploadResponse (ArquivoEntity arquivo){
        return new ArquivoUploadResponse(arquivo.getId(), arquivo.getArquivoStatus());
    }

    public ArquivoResponse toResponse (ArquivoEntity arquivo){
        return new ArquivoResponse(
                arquivo.getId(),
                arquivo.getNomeOriginal(),
                arquivo.getContentType(),
                arquivo.getTamanhoBytes(),
                arquivo.getArquivoStatus(),
                arquivo.getMensagemErro(),
                arquivo.getCriadoEm(),
                arquivo.getAtualizadoEm()
        );
    }

    public ArquivoDetalhesResponse toResponseDetalhes (ArquivoEntity arquivo){
        return new ArquivoDetalhesResponse(
                arquivo.getId(),
                arquivo.getNomeOriginal(),
                arquivo.getContentType(),
                arquivo.getTamanhoBytes(),
                arquivo.getArquivoStatus(),
                arquivo.getTextoExtraido(),
                arquivo.getMensagemErro(),
                arquivo.getCriadoEm(),
                arquivo.getAtualizadoEm()
        );
    }

}
