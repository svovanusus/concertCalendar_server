package com.ccalendar.server.error;

import com.ccalendar.server.api.data.ResultResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ChangeSetPersister.NotFoundException.class})
    public ResultResponse<String> handleNotFoundException(final Exception ex, final WebRequest request){
        return new ResultResponse<>("404!!!");
    }

    @ExceptionHandler({HttpClientErrorException.Unauthorized.class})
    public ResultResponse<String> handleUnauthorized(final Exception ex){
        return new ResultResponse<>("!!!You shell not pass!!!");
    }
}
