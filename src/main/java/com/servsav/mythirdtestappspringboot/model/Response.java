package com.servsav.mythirdtestappspringboot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    /**
     * Уникальный идентификатор сообщение
     */
    private String uid;
    /**
     * Уникальный идентификатор операции
     */
    private String operationUid;
    /**
     * Время создания сообщения
     */
    private String systemTime;
    /**
     * Код
     */
    private Codes code;
    /**
     * Годовая премия
     */
    private Double annualBonus;
    /**
     * Ежеквартальный Бонус
     */
    private Double quarterlyBonus;
    /**
     * Код ошибки
     */
    private ErrorCodes errorCode;
    /**
     * Сообщение ошибки
     */
    private ErrorMessages errorMessage;
}
