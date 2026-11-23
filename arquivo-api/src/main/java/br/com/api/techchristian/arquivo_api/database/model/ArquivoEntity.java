package br.com.api.techchristian.arquivo_api.database.model;

import br.com.api.techchristian.arquivo_api.database.enums.ArquivoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "DB_ARQUIVO")
public class ArquivoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome_original", nullable = false)
    private String nomeOriginal;

    @Column(nullable = false)
    private String caminho;

    @Column(nullable = false)
    private String contentType;

    @Column(name = "tamanho_bytes", nullable = false)
    private long tamanhoBytes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArquivoStatus arquivoStatus;

    @Column(name = "mensagem_erro", columnDefinition = "text")
    private String mensagemErro;

    @Column(name = "texto_extraido", nullable = false)
    private String textoExtraido;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private Instant criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private Instant atualizadoEm;

    @PrePersist
    void prePersist() {
        this.criadoEm = Instant.now();
        this.atualizadoEm = Instant.now();
    }

    @PreUpdate
     void preUpdate() {
        this.atualizadoEm = Instant.now();
    }
}
