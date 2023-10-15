package com.servsav.mythirdtestappspringboot.service;

import com.servsav.mythirdtestappspringboot.model.*;
import com.servsav.mythirdtestappspringboot.util.DifferenceTimeCalculator;
import java.text.ParseException;

public class ResponseFactory {

    public static Response createResponse(Request request) throws ParseException {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime("сообщение о разнице времени прошедшего с момента получения Сервисом 1 Request\n" +
                        " и временем получения модифицированного Request полученного Сервисом 2 \"" +
                        DifferenceTimeCalculator.differenceCalculate(request) + "\"")
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }
}
