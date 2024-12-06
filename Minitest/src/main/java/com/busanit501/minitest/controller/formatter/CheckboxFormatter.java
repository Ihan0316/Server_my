package com.busanit501.minitest.controller.formatter;

import lombok.extern.log4j.Log4j2;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

@Log4j2
public class CheckboxFormatter implements Formatter<Boolean> {

    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
        if(text == null){
            log.info("CheckboxFormatter : 체크가 안된 상태로 넘어올 경우");
            return false;
        }
        log.info("CheckboxFormatter : 체크가 된 상태로 넘어올 경우 text: " + text);
        boolean checkFinished = text.equals("on");
        return checkFinished;
    }

    @Override
    public String print(Boolean object, Locale locale) {
        return object.toString();
    }
}
