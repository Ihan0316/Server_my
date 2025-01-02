package com.busanit501.boot501.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Log4j2
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // 상태 코드 가져오기
        int statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 403) {
            log.info("statusCode : " + statusCode);
            return "error/403";
        } else if (statusCode == 404) {
            return "error/404";
        }
        return "error/500";
    }
}
