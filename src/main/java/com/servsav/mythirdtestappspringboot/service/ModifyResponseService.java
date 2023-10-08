package com.servsav.mythirdtestappspringboot.service;

import com.servsav.mythirdtestappspringboot.model.Response;
import org.springframework.stereotype.Service;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
