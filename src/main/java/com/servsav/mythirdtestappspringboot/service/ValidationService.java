package com.servsav.mythirdtestappspringboot.service;

import com.servsav.mythirdtestappspringboot.exception.ValidationFailedException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
}
