package com.sistempdv.pdv.validation;

import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationError extends CustomError{
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer value, String message, String requestURI) {
        super(timestamp, value, message, requestURI);
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
