package com.servsav.mythirdtestappspringboot.controller;
import com.servsav.mythirdtestappspringboot.exception.UnsupportedCodeException;
import com.servsav.mythirdtestappspringboot.exception.ValidationFailedException;
import com.servsav.mythirdtestappspringboot.model.*;
import com.servsav.mythirdtestappspringboot.service.CheckUidService;
import com.servsav.mythirdtestappspringboot.service.ModifyResponseService;
import com.servsav.mythirdtestappspringboot.service.ValidationService;
import com.servsav.mythirdtestappspringboot.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final CheckUidService checkUidService;
    private final ModifyResponseService modifyResponseService;
    @Autowired
    public MyController(ValidationService validationService,
                        CheckUidService checkUidService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService){
        this.validationService = validationService;
        this.checkUidService = checkUidService;
        this.modifyResponseService = modifyResponseService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult){
        log.info("Входящий request: {}",request);
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        log.info("Первоначальный response: {}",response);
        try {
            validationService.isValid(bindingResult);
            checkUidService.isChecked(request);
            log.info(" После валидации request: {}",request);

        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.info("Отдаваемый response: {}",response);
            log.error("Ошибка Валидации: ",e);
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch ( UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            log.info("Отдаваемый response: {}",response);
            log.error("Ошибка Не поддерживая: ",e);
            return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
        }catch ( Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.info("Отдаваемый response: {}",response);
            log.error("Ошибка Неизвестная: ",e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        log.info("Отдаваемый response: {}",response);
        return  new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }
}
