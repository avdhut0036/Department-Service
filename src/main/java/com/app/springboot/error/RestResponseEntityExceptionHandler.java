package com.app.springboot.error;

import com.app.springboot.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    /**
     * we can do lot in the exception handling if some
     * of the exceptions we don't want to handle that
     * we can by pass through this handler
     */

    @ExceptionHandler(value = {DepartmentNotFoundException.class})
    public ResponseEntity<ErrorMessage> departmentNotFoundExceptionHandler(
            WebRequest request,DepartmentNotFoundException exception) {

        String description = request.getDescription(true);
        System.out.println("description = " + description);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder().message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND).build());
    }

}
