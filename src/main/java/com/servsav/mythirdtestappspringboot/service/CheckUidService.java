package com.servsav.mythirdtestappspringboot.service;

import com.servsav.mythirdtestappspringboot.exception.UnsupportedCodeException;
import com.servsav.mythirdtestappspringboot.model.Request;
import org.springframework.stereotype.Service;

@Service
public interface CheckUidService {
    void isChecked(Request request) throws UnsupportedCodeException;
}
