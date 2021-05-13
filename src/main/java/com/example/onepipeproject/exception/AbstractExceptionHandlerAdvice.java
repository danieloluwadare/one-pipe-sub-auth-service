package com.example.onepipeproject.exception;

import com.example.onepipeproject.model.Error;
import com.example.onepipeproject.model.ErrorResponse;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AbstractExceptionHandlerAdvice {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractExceptionHandlerAdvice.class);

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> constraintViolation(DuplicateException ex) {
        logger.error("Duplicate Exception >>> " + ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getError()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OnePipeOPerationNotAllowedException.class)
    public ResponseEntity<ErrorResponse> constraintViolation(OnePipeOPerationNotAllowedException ex) {
        logger.error("Duplicate Exception >>> " + ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getError()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(OnePipeResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> constraintViolation(OnePipeResourceNotFoundException ex) {
        logger.error("Duplicate Exception >>> " + ex);
        return new ResponseEntity<>(new ErrorResponse(new Error("DO2",ex.getMessage())), HttpStatus.BAD_REQUEST);
    }

//    HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase()
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> exception(Exception exception) throws Exception {
//        if (exception instanceof AccessDeniedException
//                || exception instanceof AuthenticationException) {
//            logger.error(exception.getMessage(), exception);
//            logger.info("Throw Exception");
////            throw exception;
//        }
//        logger.error(exception.getMessage(), exception);
//        return ResponseEntity.ok("系统繁忙，请稍后再试！");
//    }


}