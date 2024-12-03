package com.busanit501.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@ControllerAdvice
public class CommonExceptionAdvice {
    // @Controller 화면, 데이터 전달
    // @RestController(ResponseBody 내재) 데이터만 전달
    // 서버에서 데이터만 전달하는 @ (http의 body에만)
    @ResponseBody
//    @ExceptionHandler(NumberFormatException.class)
//    public String exceptNumber(NumberFormatException numberFormatException) {
//        return "number format exception" + numberFormatException.getMessage();
//    }
    @ExceptionHandler(Exception.class)
    public String exceptionCommon(Exception exception) {
        log.error("예외가 발생할 경우 동작하는 클래스");
        log.error(exception.getMessage());
        // 서버 -> 웹으로 데이터만 전달 할 예정
        // return 타입이 string 이기 때문에 문자열로 데이터만 전달
        StringBuffer buffer = new StringBuffer("<ul>");
        buffer.append("<li>"+exception.getMessage()+"</li>");
        buffer.append("</ul>");

        return buffer.toString();
    }

}
