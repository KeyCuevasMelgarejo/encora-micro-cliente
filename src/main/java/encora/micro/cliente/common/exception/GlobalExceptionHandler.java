package encora.micro.cliente.common.exception;


import encora.micro.cliente.common.component.ClienteAdapter;
import encora.micro.cliente.common.util.Constants;
import encora.micro.cliente.shared.dto.response.ResponseGeneralDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseGeneralDto> handleBadRequestException(HttpMessageNotReadableException exception){
        return new ResponseEntity<>(ClienteAdapter.responseException(
                Constants.HTTP_400, HttpStatus.BAD_REQUEST.value(), exception.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseGeneralDto> handleRuntimeException(RuntimeException exception){
        return new ResponseEntity<>(ClienteAdapter.responseException(
                Constants.HTTP_500, HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseGeneralDto> handleException(Exception exception){
        return new ResponseEntity<>(ClienteAdapter.responseException(
                Constants.HTTP_500, HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseGeneralDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });


        return new ResponseEntity<>(ClienteAdapter.responseGeneral(
                Constants.HTTP_400, HttpStatus.BAD_REQUEST.value(), Constants.messageValidation, errors
        ), HttpStatus.BAD_REQUEST);
    }
}