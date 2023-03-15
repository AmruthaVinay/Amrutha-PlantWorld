/* This class contains logic related to user, authenticating user, creating user DTO to put it in session and access different pages*/

package com.perscholas.app.controller;

//import com.perscholas.app.global.CartData;
import com.perscholas.app.dto.UserDTO;
import com.perscholas.app.model.CartBasket;
import com.perscholas.app.model.LoginUser;
import com.perscholas.app.model.UserRegistration;
import com.perscholas.app.repository.UserRepository;
import com.perscholas.app.service.RegistrationService;
import com.perscholas.app.service.UserImageService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class UserController {
    @ModelAttribute("cart")
    public CartBasket initCart(){
        return new CartBasket();
    }
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserImageService userImageService;


    @GetMapping("/index")
    public String getLogin(Model model){
        model.addAttribute("message", "Hello From Login Controller");
        return "index";
    }


    @GetMapping("/index/register")
    public String redirectToIndex(Model model){

        return "redirect:/register";
    }

  /*  @GetMapping("/home")
    public String getHomePage(Principal principal,Model model){
        String email = principal.getName();
        UserRegistration user =  registrationService.findById(email);
        //log.warn(httpSession.getId());
        log.warn("User Email"+user.getUserName());
        model.addAttribute("userName",user.getUserName());
        return "redirect:/home";
    }
*/
    @GetMapping("/register")
    public String getRegister(Model model){
        model.getAttribute("user");
        // model.addAttribute("errorMsg","Wrong Userid/Password. Please try again.");

        return "register";
    }

    @GetMapping("/403")
    public String access(){
        return "403";
    }

    @GetMapping("/admin/view_all_users")
    public String redirectToAllUserInfo(Model model){
        return "redirect:/view_all_users";
    }
    @GetMapping("/view_all_users")
    public String viewAllUserInfo(Model model){
        List<UserRegistration> allUserFromDb = registrationService.findAllUsers();
        log.warn("All users in the user controleer" +allUserFromDb);
        model.addAttribute("user",allUserFromDb);
        return "view_all_users";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("userRegistration") UserRegistration userRegistration, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, @RequestParam("file") MultipartFile file) throws Exception{
        log.warn("User received in save user controller "+userRegistration);
        //System.out.println("user object"+userRegistration);
        if (userRegistration.getEmail()!=null){
            userRegistration.setEmail(userRegistration.getEmail());
        }
        userRegistration.setUserName(userRegistration.getUserName());
        userRegistration.setAddress(userRegistration.getAddress());
        userRegistration.setPassword(userRegistration.getPassword());
        UserRegistration userFromDb = registrationService.saveUser(userRegistration);

        model.addAttribute("user",userFromDb);
        model.addAttribute("message", "success");

        userImageService.save(file, userFromDb.getEmail());


        return "index";
    }

    @PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("userRegistration") UserRegistration userRegistration, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model, @RequestParam("file") MultipartFile file) throws Exception{
        log.warn("User received in save user controller "+userRegistration);
        //System.out.println("user object"+userRegistration);
        if (userRegistration.getEmail()!=null){
            userRegistration.setEmail(userRegistration.getEmail());
        }
        userRegistration.setUserName(userRegistration.getUserName());
        userRegistration.setAddress(userRegistration.getAddress());
        userRegistration.setPassword(userRegistration.getPassword());
        UserRegistration userFromDb = registrationService.saveUser(userRegistration);

        model.addAttribute("user",userFromDb);
        model.addAttribute("message", "success");

        userImageService.save(file, userFromDb.getEmail());


        return "redirect:/user_account";
    }

    @GetMapping("/update_user_form")
    public String getUpdateUserForm(@RequestParam("userDetailsFromDB") String email,Model model){
        log.warn("Update user form");
        model.addAttribute("userDetailsFromDB",registrationService.findById(email));
        return "update_user";
    }
    @GetMapping("/update_user/{email}")
    public String updateUserInfo(@PathVariable String email, RedirectAttributes redirectAttributes){
        // String emailFromPath = (String)httpSession.getAttribute("userEmail");
        log.warn("Email of user fro path"+email);
        UserRegistration userDetailsFromDB = registrationService.findById(email);
        redirectAttributes.addAttribute("userDetailsFromDB",userDetailsFromDB);
        log.warn("Update user with provided email"+userDetailsFromDB);
        return "redirect:/update_user_form";
    }

    @GetMapping("/user_account")
    public String userAccountPage(Principal principal, HttpSession httpSession,Model model){
      String email = principal.getName();
      log.warn("the email in user account controller is "+email);
        UserRegistration userDetails = registrationService.findById(email);
        model.addAttribute("userAccountDetails", userDetails);
        return "user_account";
    }

    @GetMapping("/getTourInfo")
    public String getTourInformation(Model model){
        model.addAttribute("registeredMsg","Thanks for registering, See you soon!!!");
        return "redirect:/tour";
    }



 @GetMapping("/getUser")
    public String getUser(Principal principal, HttpSession httpSession, @RequestParam(required = false) String loginEmail, String password, Model model) {
        log.warn("In getuser controller");
        UserDTO loggedInUserinfo = registrationService.findUserById(principal.getName());
        log.warn(principal.getName());
        log.warn(httpSession.getId());
        model.addAttribute("user", loggedInUserinfo);
        httpSession.setAttribute("user", loggedInUserinfo);
            return "redirect:/home";

    }

public UserDTO getUserDetails(String loginEmail){
log.warn("The userDTO is"+registrationService.findUserById(loginEmail));
        return registrationService.findUserById(loginEmail);
}


    @GetMapping("/error")
    public String getErrorPage(Model model){
        model.addAttribute("message", "Hello From Login Controller");
        return "error";
    }


}
