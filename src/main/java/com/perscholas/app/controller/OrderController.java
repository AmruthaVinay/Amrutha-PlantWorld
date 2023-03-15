/* This class contains logic related to fetching the order from db and displaying the orders placed by the user from their email*/

package com.perscholas.app.controller;

import com.perscholas.app.dto.UserDTO;
import com.perscholas.app.model.OrderBasket;
import com.perscholas.app.model.Product;
import com.perscholas.app.model.UserRegistration;
import com.perscholas.app.repository.OrderRepository;
import com.perscholas.app.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
@SessionAttributes("cart")
@Slf4j
public class OrderController {

    OrderService orderService;
    OrderRepository orderRepository;
    @Autowired
    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/view_order")
    public String getMyOrders(Principal principal, HttpSession httpSession, Model model, @ModelAttribute("order") OrderBasket orderBasket, @ModelAttribute("product") Product product){
        httpSession.getAttribute("shippingAddress");
        httpSession.getAttribute("orderedEmail");
        httpSession.getAttribute("user");
        UserDTO loggedInUserEmail = (UserDTO) httpSession.getAttribute("user");
       //String userEmail = loggedInUserEmail.getEmail();
       // log.warn("Order email in session" +orderService.findAllByUserEmail((String) httpSession.getAttribute("orderedEmail")));
        log.warn("Order email in path variable" +principal.getName());
        UserRegistration user = new UserRegistration();
        user.setEmail(principal.getName());

        List<OrderBasket> orderBasket1 = orderService.findAllByUserEmail(user);
        model.addAttribute("order",orderBasket1);

        return "view_order";




    }

    @GetMapping("/view_all_orders")
    public String getAllOrders(Principal principal, HttpSession httpSession, Model model, @ModelAttribute("order") OrderBasket orderBasket, @ModelAttribute("product") Product product){
        log.warn("Order email in path variable" +principal.getName());
        List<OrderBasket> orderBasket1 = orderRepository.findAll();
        model.addAttribute("order",orderBasket1);
        return "view_all_orders";

    }


}
