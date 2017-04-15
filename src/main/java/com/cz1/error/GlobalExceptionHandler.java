package com.cz1.error;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by wkchen on 2017/4/14.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected final Log logger = LogFactory.getLog(getClass());


    /**
     * User Aleady Exists
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(UserAlreadyExistsExcepition.class)
    public ResponseEntity<?> handlerException(UserAlreadyExistsExcepition ex) {
        logger.debug(ex.getMessage());
        logger.trace(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error("User Aleady Exists"));
    }

    /**
     * Not Found
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerException(NotFoundException ex) {
        logger.debug(ex.getMessage());
        logger.trace(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("Not Found"));
    }

    /**
     * File Exception
     * @param ex
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> handlerException(MultipartException ex) {
        logger.debug(ex.getMessage());
        logger.trace(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(ex.getMessage()));
    }
}
