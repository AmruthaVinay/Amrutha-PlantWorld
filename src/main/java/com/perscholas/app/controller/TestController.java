/* This class contains logic related to testing*/

package com.perscholas.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@Slf4j
@SessionAttributes(value = {"msg"})
public class TestController {



    @GetMapping("/model")
    public String model(HttpSession http, Model model, @ModelAttribute("msg") String msg){
        log.warn("first call: " + msg);
        log.warn(http.getId());
        model.addAttribute("msg","hello world 222");
        log.warn("containsAttribute(\"msg\")" + String.valueOf(model.containsAttribute("msg")));
        log.warn((String) model.getAttribute("msg"));



        return "model_page";
    }



    @GetMapping("/studentform")
    public String studentForm(Model model, @SessionAttribute("msg") String msg, HttpSession http){

        log.warn("student form method");
        log.warn("second call: " + msg);
        log.warn(http.getId());
        model.addAttribute("msg","hello world 333");
        log.warn("containsAttribute(\"msg\")" + String.valueOf(model.containsAttribute("msg")));
        log.warn((String) model.getAttribute("msg"));

        return "form";
    }




    @GetMapping("/getform")
    public String getTheForm(Model model, HttpSession http){
        log.warn("third call: " + model.getAttribute("msg"));
        log.warn(http.getId());
        model.addAttribute("msg","hello world 444");
        log.warn("containsAttribute(\"msg\")" + String.valueOf(model.containsAttribute("msg")));
        log.warn((String) model.getAttribute("msg"));

        return "form_requestparam";
    }




}
