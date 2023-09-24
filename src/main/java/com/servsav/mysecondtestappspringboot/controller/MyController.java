package com.servsav.mysecondtestappspringboot.controller;

import com.servsav.mysecondtestappspringboot.MySecondTestAppSpringBootApplication;
import com.servsav.mysecondtestappspringboot.exception.UnsupportedCodeException;
import com.servsav.mysecondtestappspringboot.exception.ValidationFailedException;
import com.servsav.mysecondtestappspringboot.model.Request;
import com.servsav.mysecondtestappspringboot.model.Response;
import com.servsav.mysecondtestappspringboot.service.CheckUidService;
import com.servsav.mysecondtestappspringboot.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class MyController {
    private  final ValidationService validationService;
    private  final CheckUidService checkUidService;
    @Autowired
    public MyController(ValidationService validationService, CheckUidService checkUidService){
        this.validationService = validationService;
        this.checkUidService = checkUidService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(simpleDateFormat.format(new Date()))
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        try {
            validationService.isValid(bindingResult);
            checkUidService.isChecked(request);

        } catch (ValidationFailedException e) {
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage("Ошибка валидации. "+ e.getMessage());
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch ( UnsupportedCodeException e) {
            response.setCode("failed");
            response.setErrorCode("UnsupportedCodeException");
            response.setErrorMessage("Не поддерживаемая ошибка." + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
        }catch ( Exception e) {
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Произошла неизвестная ошибка. ");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);

    }
}
