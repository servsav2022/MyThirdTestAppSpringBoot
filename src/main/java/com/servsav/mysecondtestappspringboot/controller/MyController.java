package com.servsav.mysecondtestappspringboot.controller;

import com.servsav.mysecondtestappspringboot.MySecondTestAppSpringBootApplication;
import com.servsav.mysecondtestappspringboot.exception.UnsupportedCodeException;
import com.servsav.mysecondtestappspringboot.exception.ValidationFailedException;
import com.servsav.mysecondtestappspringboot.model.*;
import com.servsav.mysecondtestappspringboot.service.CheckUidService;
import com.servsav.mysecondtestappspringboot.service.ModifyResponseService;
import com.servsav.mysecondtestappspringboot.service.ValidationService;
import com.servsav.mysecondtestappspringboot.util.DateTimeUtil;
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
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {
    private  final ValidationService validationService;
    private  final CheckUidService checkUidService;
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
        log.error("1 bindingResult: {}",bindingResult);
        log.info("1 request: {}",request);
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("1 response: {}",response);

        try {
            validationService.isValid(bindingResult);
            log.info("2 bindingResult: {}",bindingResult);
            checkUidService.isChecked(request);
            log.info("2 request: {}",request);

        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);

            log.error("Ошибка: ",e);
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch ( UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);

            log.error("Ошибка: ",e);
            return new ResponseEntity<>(response, HttpStatus.SEE_OTHER);
        }catch ( Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);

            log.error("Ошибка: ",e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);
        log.info("2 response: {}",response);
        return  new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);

    }
}
