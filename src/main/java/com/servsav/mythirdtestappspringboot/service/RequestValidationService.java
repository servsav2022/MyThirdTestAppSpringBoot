package com.servsav.mythirdtestappspringboot.service;

import com.servsav.mythirdtestappspringboot.exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
@Slf4j
@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            log.error("Ошибка в bindingResult: {}",bindingResult);
            throw new
                    ValidationFailedException(bindingResult.getFieldError().toString());

        }
    }
}
