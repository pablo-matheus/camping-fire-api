package br.com.campingfire.exception;

import br.com.campingfire.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestControllerAdvice
public class RestExceptionHandler {

    private final MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorResponse> handle(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ErrorResponse> errorResponses = new ArrayList();

        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, Locale.ENGLISH);
            ErrorResponse errorResponse = new ErrorResponse(e.getField(), message);
            errorResponses.add(errorResponse);
        });

        return errorResponses;

    }

}
