package com.servsav.mythirdtestappspringboot.service;

import com.servsav.mythirdtestappspringboot.model.Response;
import com.servsav.mythirdtestappspringboot.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Qualifier("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService
        implements ModifyResponseService {
    @Override
    public Response modify(Response response) {
        response.setSystemTime(DateTimeUtil.getCustomFormat()
                .format(new Date()));
    return response;
    }
}
