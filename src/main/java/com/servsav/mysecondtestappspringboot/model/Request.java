package com.servsav.mysecondtestappspringboot.model;
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
    private String uid;

    @Size(max = 32)
    @NotBlank (message = "Не заполнено поле operationUid")
    private String operationUid;
    private Systems systemName;

    @NotBlank (message = "Не заполнено поле systemTime")
    private String systemTime;
    private String source;

    @Min(1)
    @Max(100000)
    private int communicationId;
    private int templateId;
    private int productCode;
    private int smsCode;
    @Override
    public  String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                "}";
    }
}
