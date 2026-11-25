package br.com.api.techchristian.arquivo_api.exception;

public class ArquivoNaoEncontradoException extends RuntimeException {
    public ArquivoNaoEncontradoException(String message) {
        super(message);
    }
}
