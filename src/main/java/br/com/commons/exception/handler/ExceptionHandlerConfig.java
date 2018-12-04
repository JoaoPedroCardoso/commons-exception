package br.com.commons.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;

public class ExceptionHandlerConfig {

    @Autowired
    private MessageSource messageSource;

    @Bean
    public ExceptionHandlingControllerAdvice createExceptionHandler() {
        return new ExceptionHandlingControllerAdvice(messageSource);
    }
}
