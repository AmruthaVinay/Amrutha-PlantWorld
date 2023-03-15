/* this class contains logic related to placing order to db*/

package com.perscholas.app.controller;

//import com.perscholas.app.global.CartData;
import com.perscholas.app.model.CartBasket;
import com.perscholas.app.model.OrderBasket;
import com.perscholas.app.model.Product;
import com.perscholas.app.model.UserRegistration;
import com.perscholas.app.repository.OrderRepository;
import com.perscholas.app.repository.ProductRepository;
//import com.perscholas.app.service.CartService;
import com.perscholas.app.service.OrderService;
import com.perscholas.app.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("cart")
@Slf4j
public class CheckOutController {
    @Autowired
    ProductService productService;
  /*  @Autowired
    //CartService cartService;*/
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public String showOrderPage(HttpSession httpSession, String email, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, Model model,@ModelAttribute("product") Product product) {
        model.addAttribute("cartCount", cartBasket.size());
        model.addAttribute("cart", cartBasket);
        model.addAttribute("total", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        httpSession.setAttribute("orderedEmail", email);
        httpSession.setAttribute("cart", cartBasket);
        httpSession.setAttribute("product", cartBasket.stream().map(CartBasket::getCartProducts).collect(Collectors.toList()));
        httpSession.setAttribute("cartCount", cartBasket.size());
        httpSession.setAttribute("total", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        log.warn("confirm order call: " + cartBasket);

        log.warn("confirm order call product: " + httpSession.getAttribute("product"));
        return "checkout";
    }


    @PostMapping("/order")
    public String orderItems(Principal principal, HttpSession httpSession, @RequestParam(required = false) String shippingAddress, @RequestParam(required = false) String email, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, Model model, @ModelAttribute("product") Product product){
        model.addAttribute("cartCount",cartBasket.size());
        model.addAttribute("cart", cartBasket);
        model.addAttribute("total", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        model.addAttribute("totalPlusTax", (cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*6.25)/100+cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        log.warn("in order session totalplus tax"+httpSession.getAttribute("totalPlusTax"));
        log.warn("in order session model totalplus tax"+model.getAttribute("totalPlusTax"));
        log.warn("in order session model actual calculation totalplus tax"+cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*6.25/100+cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        httpSession.setAttribute("shippingAddress", shippingAddress);
        httpSession.setAttribute("orderedEmail", email);
        httpSession.getAttribute("cart");
        httpSession.getAttribute("cartCount");
        httpSession.getAttribute("total");
        httpSession.getAttribute("shippingAddress");
        httpSession.getAttribute("totalPlusTax");
        String orderShipping = (String) httpSession.getAttribute(shippingAddress);
        cartBasket.forEach(s -> s.setShippingAddress(orderShipping));
        log.warn(httpSession.getId());

        log.warn("order call product: " +httpSession.getAttribute("product"));
        log.warn("order call shippingAddress: " +httpSession.getAttribute("shippingAddress"));

        //orderBasket.setShippingAddress(shippingAddress);
        OrderBasket orderBasket = new OrderBasket();
         double totalPlusTax =(Double) model.getAttribute("totalPlusTax");
        orderBasket.setTotalOrderPrice((double)Math.round(totalPlusTax*100)/100);
        log.warn("in order totalplus tax thats send to db"+orderBasket.getTotalOrderPrice());
        orderBasket.setOrdered_date(new Date());
        orderBasket.setUserSessionId((String)httpSession.getId());
        UserRegistration orderedUserEmail = new UserRegistration();
        //orderedUserEmail.setEmail((String) httpSession.getAttribute("shippingAddress"));
        //orderBasket.setCustomer(orderedUserEmail);
        orderBasket.setShippingAddress((String) httpSession.getAttribute("shippingAddress"));
        UserRegistration orderingUser = new UserRegistration();
        //List<Product> products = httpSession.getAttribute("product");
        orderingUser.setEmail(principal.getName());
        orderBasket.setUserEmail(orderingUser);

       List<Product> productInCat = cartBasket.stream().map(CartBasket::getCartProducts).collect(Collectors.toList());
      log.warn("productInCat in order: " +productInCat);
        for(Product item :productInCat) {
            orderBasket.getUserEmail().setProducts(item);
            //orderingUser.setProducts(item);
        }
        for(Product item :productInCat) {

            orderingUser.setProducts(item);
        }

        orderBasket.setProducts(cartBasket.stream().map(CartBasket::getCartProducts).collect(Collectors.toList()));
        log.warn("confirm order call shipping address: " + shippingAddress);
        log.warn("order call orderbasket: " + orderBasket);
        log.warn("order call order to diaplay orders in ordering user: " +  orderBasket.getUserEmail());


        orderService.saveOrder(orderBasket);
        log.warn("Before Session id"+httpSession.getId());
     if (httpSession != null) {

         httpSession.setAttribute("cart", cartBasket.removeAll(cartBasket));

            log.warn("Removing attribute");
        }
        log.warn("After session id"+httpSession.getId());
        return "order";
    }


}
