package com.crud.webflux.demowebflux.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomResponse extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new HashMap<>();
        Throwable throwable = super.getError(request);
        if (throwable instanceof CustomException customException) {
            errorAttributes.put("status", customException.getStatus().value());
            errorAttributes.put("message", customException.getMessage());
            return errorAttributes;
        }
        return errorAttributes;
    }
}