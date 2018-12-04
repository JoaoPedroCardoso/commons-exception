package br.com.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor(staticName = "of")
public class IntegrationException extends RuntimeException {

    public static final String PLACEHOLDER = "exception.integration";

    @NonNull
    private final String code;

    @NonNull
    private final String message;

}

