package br.com.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor(staticName = "of")
public class BusinessException extends RuntimeException {

    @NonNull
    private final String code;

    @NonNull
    private final String placeholder;

}
