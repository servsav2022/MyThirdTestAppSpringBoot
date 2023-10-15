package com.servsav.mythirdtestappspringboot.controller;
import com.servsav.mythirdtestappspringboot.exception.UnsupportedCodeException;
import com.servsav.mythirdtestappspringboot.exception.ValidationFailedException;
import com.servsav.mythirdtestappspringboot.model.*;
import com.servsav.mythirdtestappspringboot.service.*;
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
import java.text.ParseException;


@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final CheckUidService checkUidService;
    private final ModifyResponseService modifyResponseService;
    @Autowired
    /**
     * Используется @Qualifier("ModifyOperationUidResponseService") для того что видеть разницу вовремени или он
     * перезатрется в методе modify другого @Qualifier
     */
    public MyController(ValidationService validationService,
                        CheckUidService checkUidService,
                        @Qualifier("ModifyOperationUidResponseService") ModifyResponseService modifyResponseService){
        this.validationService = validationService;
        this.checkUidService = checkUidService;
        this.modifyResponseService = modifyResponseService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) throws ParseException {
        log.info("Входящий request: {}",request);
        Response response = ResponseFactory.createResponse(request);
        try {
            validationService.isValid(bindingResult);
            checkUidService.isChecked(request);
            log.info("После валидации request: {}", request);
        } catch (ValidationFailedException e) {
            return ErrorHandling.handleValidationException(response, e);
        } catch (UnsupportedCodeException e) {
            return ErrorHandling.handleUnsupportedCodeException(response, e);
        } catch (Exception e) {
            return ErrorHandling.handleUnknownException(response, e);
        }
        modifyResponseService.modify(response);
        log.info("Отдаваемый response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
