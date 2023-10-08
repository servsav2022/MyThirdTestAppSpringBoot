package com.servsav.mythirdtestappspringboot.service;

import com.servsav.mythirdtestappspringboot.exception.UnsupportedCodeException;
import com.servsav.mythirdtestappspringboot.model.Request;
import org.springframework.stereotype.Service;
import java.util.Objects;
@Service
public class RequestCheckUidService implements CheckUidService {

    @Override
    public void isChecked (Request request) throws UnsupportedCodeException {
        if (Objects.equals(request.getUid(), "123")) {
            throw new UnsupportedCodeException(" Не поддерживаемая ошибка -" +
                    " это сообщение из Сервиса" );
        }
    }
}
