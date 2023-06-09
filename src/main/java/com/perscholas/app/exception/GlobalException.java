package com.perscholas.app.exception;

import com.perscholas.app.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler({SQLException.class, DataAccessException.class, Exception.class, SQLSyntaxErrorException.class, AppException.class})
    public RedirectView allExceptions(AppException ex){

        Map<String,String> map = new LinkedHashMap<>();
        map.put(LocalDateTime.now().toString(),ex.getMessage());
        log.debug(map.toString());
        ex.printStackTrace();

        return new RedirectView("/index");
    }

    @ModelAttribute
    public void initModel(Model model){

        model.addAttribute("msg","hello world");

    }
}