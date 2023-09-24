package com.servsav.mysecondtestappspringboot.service;

import com.servsav.mysecondtestappspringboot.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
