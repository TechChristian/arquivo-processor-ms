package br.com.api.techchristian.arquivo_api.database.repository;

import br.com.api.techchristian.arquivo_api.database.model.ArquivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IArquivoRepository extends JpaRepository<ArquivoEntity, UUID> {
}
