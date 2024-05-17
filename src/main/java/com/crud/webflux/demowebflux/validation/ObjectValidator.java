package com.crud.webflux.demowebflux.validation;

import com.crud.webflux.demowebflux.exception.CustomException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ObjectValidator {

    private final Validator validator;

    @SneakyThrows
    public <T> T validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> errors = validator.validate(object);
        if (errors.isEmpty()){
            return object;
        } else {
            String message = errors.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            throw new CustomException(HttpStatus.BAD_REQUEST, message);
        }

    }
}
