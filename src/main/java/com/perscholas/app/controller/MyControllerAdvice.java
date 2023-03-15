/* This class contains logic related to exception handling through spring*/

package com.perscholas.app.controller;


import com.perscholas.app.model.AuthGroup;
import com.perscholas.app.model.UserRegistration;

import com.perscholas.app.repository.AuthGroupRepoI;
import com.perscholas.app.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@ControllerAdvice
@Slf4j
public class MyControllerAdvice {
    @Autowired
    private AuthGroupRepoI authGroupRepoI;
    @Autowired
     UserRepository userRepository;


    @ExceptionHandler(Exception.class)
    public RedirectView exceptionHandler(Exception ex){
    ex.printStackTrace();
    RedirectView redirectView = new RedirectView();
    redirectView.setUrl("http://localhost:8080/error");
    return redirectView;

    }

    public void getRolefromAuthGroup(Model model, HttpServletRequest request, HttpSession http){
        Principal userPrincipal = request.getUserPrincipal();
       List<AuthGroup> authGroups =  authGroupRepoI.findByEmail(userPrincipal.getName());
       log.warn("Authgroup from db"+authGroups);
        UserRegistration userRegistration = null;
        if(userPrincipal != null) {
            userRegistration = userRepository.findByEmail(userPrincipal.getName()).get();

        }
        log.warn("User from db"+userRepository.findByEmail(userPrincipal.getName()));
    }


}