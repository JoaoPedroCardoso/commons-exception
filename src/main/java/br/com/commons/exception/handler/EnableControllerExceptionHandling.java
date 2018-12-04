package br.com.commons.exception.handler;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Import({
        ExceptionHandlerConfig.class
})
public @interface EnableControllerExceptionHandling {
}
