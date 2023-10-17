package com.servsav.mythirdtestappspringboot.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Size(max = 32)
    @NotBlank (message = "Не заполнено поле uid")
    /**
     * Уникальный идентификатор сообщение
     */
    private String uid;

    @Size(max = 32)
    @NotBlank (message = "Не заполнено поле operationUid")
    /**
     * Уникальный идентификатор операции
     */
    private String operationUid;
    /**
     *Имя системы отправителя
     */
    private Systems systemName;

    @NotBlank (message = "Не заполнено поле systemTime")
    /**
     * Время создания сообщения
     */
    private String systemTime;

    /**
     * Наименование ресурса
     */
    private String source;

    /**
     * Должность
     */
    private Positions position;
    /**
     * оклад
     */
    private Double salary;
    /**
     * премия
     */
    private Double bonus;
    /**
     * кол-во рабочих дней в году
     */
    private Integer workDays;

    @Min(1)
    @Max(100000)
    /**
     * Уникальный идентификатор коммуникации
     */
    private int communicationId;
    /**
     * Уникальный идентификатор шаблона
     */
    private int templateId;
    /**
     * Код продукта
     */
    private int productCode;
    /**
     * Смс код
     */
    private int smsCode;
    @Override
    public  String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", workDays=" + workDays +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                "}";
    }
}