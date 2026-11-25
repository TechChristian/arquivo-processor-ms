package br.com.api.techchristian.arquivo_api.handler;

import br.com.api.techchristian.arquivo_api.exception.ArmazenamentoException;
import br.com.api.techchristian.arquivo_api.exception.ArquivoInvalidoException;
import br.com.api.techchristian.arquivo_api.exception.ArquivoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ArquivoInvalidoException.class)
    public ResponseEntity<ErrorMessage> arquivoInvalidoException(ArquivoInvalidoException ex, HttpServletRequest request){
        log.warn("Arquivo inválido: {}", ex.getMessage());
        return
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(ArmazenamentoException.class)
    public ResponseEntity<ErrorMessage> armazenamentoException(ArmazenamentoException ex, HttpServletRequest request){
        log.error("Falha ao armazenar o arquivo", ex);
        return
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao armazenar o arquivo."));
    }

    @ExceptionHandler(ArquivoNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> arquivoNaoEncontradoException(ArquivoNaoEncontradoException ex, HttpServletRequest request){
        log.warn("Arquivo não encontrado: {}", ex.getMessage());
        return
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, BindingResult result){
        log.warn(ex.getMessage());
        return
                ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Requisição inválida", result));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorMessage> maxUploadSizeExceededException(MaxUploadSizeExceededException ex, HttpServletRequest request){
        log.warn("Upload acima do limite permitido");
        return
                ResponseEntity.status(HttpStatus.CONTENT_TOO_LARGE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ErrorMessage(request, HttpStatus.CONTENT_TOO_LARGE, "O arquivo excede o limite permitido"));
    }
}
