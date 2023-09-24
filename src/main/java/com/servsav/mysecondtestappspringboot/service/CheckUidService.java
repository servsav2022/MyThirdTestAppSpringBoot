package com.servsav.mysecondtestappspringboot.service;

import com.servsav.mysecondtestappspringboot.exception.UnsupportedCodeException;
import com.servsav.mysecondtestappspringboot.exception.ValidationFailedException;
import com.servsav.mysecondtestappspringboot.model.Request;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface CheckUidService {
    void isChecked(Request request) throws UnsupportedCodeException;
}
