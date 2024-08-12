package br.com.desafio.controller.exception;

import br.com.desafio.controller.dto.ResponseAPI;
import br.com.desafio.exception.PlanetaException;
import br.com.desafio.exception.SondaException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Tratamento para exceções do tipo planeta
     *
     * @param ex
     * @return ResponseAPI
     */
    @ExceptionHandler(PlanetaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseAPI planetaExceptionHandler(PlanetaException ex) {
        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .data(ex.getObject())
                .build();
    }

    /**
     * Tratamento para exceções do tipo sonda
     *
     * @param ex
     * @return ResponseAPI
     */
    @ExceptionHandler(SondaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseAPI sondaExceptionHandler(SondaException ex) {
        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .data(ex.getObject())
                .build();
    }

    /**
     * Tratamento para parametros não encontrados
     *
     * @param ex
     * @return ResponseAPI
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseAPI missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        String message = ex.getParameterName() + " parametro não encontrado";

        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .build();
    }

    /**
     * Tratamento para parametros com tipo errado
     *
     * @param ex
     * @return ResponseAPI
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseAPI methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        String message = ex.getName() + " deve ser do tipo " + ex.getRequiredType().getName();

        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .build();
    }

    /**
     * Tratamento para parametros tratados com javax validation
     *
     * @param ex
     * @return ResponseAPI
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseAPI methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .message("Campo invalido!")
                .errors(errors)
                .build();
    }

    /**
     * Tratamento para violações de restrições
     *
     * @param ex
     * @return ResponseAPI
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseAPI constraintViolationExceptionHandler(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .message("Restrição violada")
                .errors(errors)
                .build();
    }

    /**
     * Tratamento para verbos acionado pelo cliente que não foi implantado
     *
     * @param ex
     * @return ResponseAPI
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseAPI httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .message("O verbo " +  ex.getMethod() + " não é suportado")
                .build();
    }

    /**
     * Tratamento de HttpMessageNotReadableException
     *
     * @param ex
     * @return ResponseAPI
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseAPI httpMessageNotReadableException(HttpMessageNotReadableException ex) {

        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
    }

    /**
     * Tratamento de violação de constraint
     *
     * @param ex
     * @return ResponseAPI
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseAPI dataIntegrityViolationException(DataIntegrityViolationException ex){
        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .message("Não foi possível executar a querie. Violação de constraint.")
                .build();
    }

    /**
     * Tratamento para qualquer exceção não mapeada
     *
     * @param ex
     * @return ResponseAPI
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseAPI exceptionHandler(Exception ex) {
        ex.printStackTrace();

        return ResponseAPI.builder()
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
    }
}
