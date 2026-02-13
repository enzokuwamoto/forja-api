package project.forja.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import project.forja.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleJsonErrors(HttpMessageNotReadableException ex) {
        Map<String, String> errorResponse = new HashMap<>();

        // Tratamento de tipos incorretos, retorna o campo do JSON que está incorreto.
        if (ex.getCause() instanceof InvalidFormatException) {
            var ife = (com.fasterxml.jackson.databind.exc.InvalidFormatException) ex.getCause();

            String fieldName = ife.getPath().stream()
                    .map(ref -> {
                        if (ref.getFieldName() != null) return ref.getFieldName();
                        if (ref.getIndex() >= 0) return "[" + ref.getIndex() + "]";
                        return "?";
                    })
                    .collect(Collectors.joining("."));

            // Tratamento para ENUM incorreto
            String detalheErro;
            if (ife.getTargetType() != null && ife.getTargetType().isEnum()) {
                String opcoesAceitas;
                opcoesAceitas = Arrays.stream(ife.getTargetType().getEnumConstants())
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
                detalheErro = String.format("O valor '%s' não é aceito. Opções válidas: [%s]", ife.getValue(), opcoesAceitas);
            } else {
                detalheErro = String.format("O valor '%s' não é aceito para este campo. Esperava-se tipo '%s'.",
                        ife.getValue(), ife.getTargetType().getSimpleName());
            }

            errorResponse.put("campo", fieldName);
            errorResponse.put("erro", detalheErro);

        } else {
            // Erro de sintaxe (faltou chave, vírgula, aspas)
            errorResponse.put("erro", "O JSON está mal formatado ou ilegível.");
        }

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        String message = String.format("O método %s não é permitido para esta rota.", ex.getMethod());
        errorResponse.put("erro", message);
        // Retornar os métodos aceitos
        if (ex.getSupportedHttpMethods() != null) {
            errorResponse.put("metodos_aceitos", ex.getSupportedHttpMethods().toString());
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEntry(DataIntegrityViolationException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        String message = "Já existe um registro com este dado único (e-mail) no sistema.";

        if (ex.getMessage() != null && ex.getMessage().contains("Duplicate entry")) {
            message = "Este registro já existe no banco de dados.";
        }

        errorResponse.put("erro", "Conflito de dados");
        errorResponse.put("detalhe", message);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        System.out.println("Erro não tratado: " + ex.getClass().getName());
        ex.printStackTrace();
        return ResponseEntity.internalServerError()
                .body("Erro interno no servidor. Contate o suporte.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundHandler(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User não encontrado");
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> ResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getReason());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> DuplicateData(SQLIntegrityConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro duplicado.");
    }

}


