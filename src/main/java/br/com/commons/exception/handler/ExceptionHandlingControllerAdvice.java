package br.com.commons.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.commons.exception.BusinessException;
import br.com.commons.exception.IntegrationException;
import br.com.commons.exception.response.ErrorMessageResponse;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    private final MessageSource messageSource;
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String DEFAULT_INTEGRATION_ERROR_MESSAGE = "Integration failure";

    public ExceptionHandlingControllerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<ErrorMessageResponse> handleBusinessException(BusinessException exception) {

        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.of(
                exception.getCode(),
                resolveMessagePlaceholder(exception.getPlaceholder())
        );

        log.warn("Business exception: {}", errorMessageResponse.getMessage());

        return ResponseEntity.badRequest().body(errorMessageResponse);
    }

    @ExceptionHandler(IntegrationException.class)
    private ResponseEntity<ErrorMessageResponse> handleIntegrationException(IntegrationException exception) {

        ErrorMessageResponse errorMessageResponse = ErrorMessageResponse.of(
                exception.getCode(),
                resolveMessagePlaceholder(IntegrationException.PLACEHOLDER, DEFAULT_INTEGRATION_ERROR_MESSAGE)
        );

        log.error("Integration exception: {}", exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessageResponse);

    }

    private String resolveMessagePlaceholder(String placeholder) {
        return messageSource.getMessage(placeholder, null, LocaleContextHolder.getLocale());
    }

    private String resolveMessagePlaceholder(String placeholder, String defaultValue) {
        return messageSource.getMessage(placeholder, null, defaultValue, LocaleContextHolder.getLocale());
    }

}
