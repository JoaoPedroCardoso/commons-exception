package br.com.commons.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class ErrorMessageResponse {

    private final String code;
    private final String message;

}
