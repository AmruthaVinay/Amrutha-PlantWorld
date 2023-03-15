/* This class contains logic related to adding the items to cart, deleting the items from cart and displaying the home page*/

package com.perscholas.app.controller;

//import com.perscholas.app.global.CartData;
import com.perscholas.app.dto.UserDTO;
import com.perscholas.app.global.GlobalData;
import com.perscholas.app.model.CartBasket;
import com.perscholas.app.model.Product;
//import com.perscholas.app.service.CartService;
import com.perscholas.app.model.UserRegistration;
import com.perscholas.app.service.CategoryService;
import com.perscholas.app.service.ProductService;
import com.perscholas.app.service.RegistrationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("cart")
@Slf4j
public class CartController {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    @ModelAttribute("cart")
    public List<CartBasket> initCart(){
        return new ArrayList<>();
    }


    ProductService productService;

    CategoryService categoryService;

    RegistrationService registrationService;

    //CartService cartService;
    @Autowired
    public CartController(ProductService productService, CategoryService categoryService, RegistrationService registrationService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.registrationService = registrationService;
    }


    @GetMapping(path = {"/search"})
    public String home(Product product, Model model, String keyword) {
        if(keyword!=null) {
            List<Product> productSearch = productService.findByName(keyword);
            model.addAttribute("product", productService.findByName(keyword));
        }
        return "home";
    }
 @GetMapping("/home")
 public String showAll(Principal principal, HttpSession httpSession , Model model, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, @ModelAttribute("product") Product product) {
     //model.addAttribute("user",registrationService.findUserById(principal.getName()));
     model.addAttribute("product", productService.getAllProductsByCategoryId(GlobalData.CategoryIdentifier.SALE.category_identifier));
     model.addAttribute("cartCount", cartBasket.size());
     model.addAttribute("cart", cartBasket);
     httpSession.setAttribute("cart", cartBasket);
     log.warn(httpSession.getId());
     log.warn("home call: " + cartBasket);
     // model.addAttribute("cartCount", CartData.cart.size());
     //model.addAttribute("loggedInUser", CartData.userInfo.getUserName());
     return "home";
 }

    @GetMapping("/indoor_plants")
    public String showIndoorPlants(HttpSession httpSession, Model model, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, @ModelAttribute("product") Product product) {
        model.addAttribute("product", productService.getAllProductsByCategoryId(GlobalData.CategoryIdentifier.INDOOR_PLANTS.category_identifier));
        model.addAttribute("cartCount",cartBasket.size());
        model.addAttribute("cart", cartBasket);
        httpSession.setAttribute("cart",cartBasket);
        // model.addAttribute("cartCount", CartData.cart.size());
        //model.addAttribute("loggedInUser", CartData.userInfo.getUserName());
        return "indoor_plants";
    }

    @GetMapping("/herbs")
    public String requestHerbsPage(Principal principal, HttpSession httpSession , Model model, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, @ModelAttribute("product") Product product) {
        model.addAttribute("product", productService.getAllProductsByCategoryId(GlobalData.CategoryIdentifier.HERBS.category_identifier));
        model.addAttribute("cartCount",cartBasket.size());
        model.addAttribute("cart", cartBasket);
        httpSession.setAttribute("cart",cartBasket);
     return "herbs";
    }
    @GetMapping("/outdoor_plants")
    public String requestOutDoorPlantsPage(Principal principal, HttpSession httpSession , Model model, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, @ModelAttribute("product") Product product) {
        model.addAttribute("product", productService.getAllProductsByCategoryId(GlobalData.CategoryIdentifier.OUTDOOR_PLANTS.category_identifier));
        model.addAttribute("cartCount",cartBasket.size());
        model.addAttribute("cart", cartBasket);
        httpSession.setAttribute("cart",cartBasket);

        return "outdoor_plants";
    }
    @GetMapping("/pots_tools")
    public String requestPotsAndToolsPage(Principal principal, HttpSession httpSession , Model model, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, @ModelAttribute("product") Product product) {
        model.addAttribute("product", productService.getAllProductsByCategoryId(GlobalData.CategoryIdentifier.POTS_AND_TOOLS.category_identifier));
        model.addAttribute("cartCount",cartBasket.size());
        model.addAttribute("cart", cartBasket);
        httpSession.setAttribute("cart",cartBasket);
        return "pots_tools";
    }

    @GetMapping("/removeFromCart/{id}")
    public String deleteFromToCart(HttpSession httpSession, @PathVariable Integer id, Model model, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, @ModelAttribute("product") Product product){
        //CartData.cart.remove(productService.getProductById(id).get());
        log.warn("before remove from cart call cartbasket: " + cartBasket);
        model.addAttribute("total", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        model.addAttribute("cartCount",cartBasket.size());
        model.addAttribute("cart", cartBasket);
        model.addAttribute("total", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        Product productToBeRemoved = productService.getProductById(id).get();
        log.warn("before remove from cart call cartbasket: " + cartBasket.stream().map(CartBasket::getCartProducts).collect(Collectors.toList()));
        List<Product> productInCat = cartBasket.stream().map(CartBasket::getCartProducts).collect(Collectors.toList());
        log.warn("before remove productInCat: " +productInCat);
        int index=0;
            for(Product item :productInCat){
                if(productToBeRemoved.getId().equals(item.getId())){
                log.warn("in remove loop");
                    index = productInCat.indexOf(productToBeRemoved);
                    log.warn("index in loop"+index);
                }
            }
        cartBasket.remove(index);
        log.warn("after remove productInCat: " +productInCat);
        log.warn("after remove cartBasket: " +cartBasket);
        return "redirect:/confirm_order";
    }
    @PostMapping("/addToCart/{id}")
    public String addToCart(HttpSession httpSession, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, @PathVariable Integer id, Model model, @Valid @ModelAttribute("product") Product product,  BindingResult bindingResult){
        log.warn("quantity in product.getQuantity() ----------------"+product.getQuantity());
        if (bindingResult.hasErrors()) {
            log.warn("has error" + bindingResult.getAllErrors());
            bindingResult.getAllErrors();
            return "redirect:/home";
        }
        Product product1 = productService.getProductById(id).get();
        product1.setQuantity(product.getQuantity());
      //  log.warn("quantity in path variable  ----------------"+quantity);
       log.warn("product in addtocart model----------------"+product);
        log.warn("addtocart call: " + cartBasket);


        UserRegistration user = new UserRegistration();

        log.warn("quantity in path variable product1.setQuantity  ----------------"+product1.getQuantity());
        log.warn("product in addtocart ----------------"+product1);
       // product1.getUser_email().setProducts(product1);
        List<Product> cartProducts = new ArrayList<>();
        cartProducts.add(product1);
        CartBasket newCartBasket = new CartBasket();
        if (cartBasket != null && product.getQuantity()>1){
            log.warn("product in addtocart existing----------------"+product);
            newCartBasket.setProductQuantity(product.getQuantity());
            newCartBasket.setProductPrice(product1.getPrice()*product.getQuantity());
            newCartBasket.setProductImage(product1.getImage());
            newCartBasket.setProductName(product1.getName());
            newCartBasket.setCartProducts(product1);

            user.setProducts(product1);
            newCartBasket.setUserProductsOrdered(user);

           log.warn("user object orders --------------"+newCartBasket.getUserProductsOrdered());

            newCartBasket.setEstimatedTax(cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*6.25/100);
            newCartBasket.setTotalPlusTax((cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*newCartBasket.getEstimatedTax())/100+cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
            cartBasket.add(newCartBasket);
            }

        else{
            newCartBasket.setProductQuantity(product.getQuantity());
            newCartBasket.setCartProducts(product1);
            newCartBasket.setProductPrice(product1.getPrice());
            newCartBasket.setProductImage(product1.getImage());
            newCartBasket.setProductName(product1.getName());
            newCartBasket.setEstimatedTax(cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*6.25/100);
            newCartBasket.setTotalPlusTax((cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*newCartBasket.getEstimatedTax())/100+cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
            user.setProducts(product1);
            newCartBasket.setUserProductsOrdered(user);
            cartBasket.add(newCartBasket);


        }
        System.out.println("----------Cart Size"+cartBasket.size());
        System.out.println("-----------Cart basket"+cartBasket);
        model.addAttribute("cartCount", cartBasket.size());
        model.addAttribute("total", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        model.addAttribute("estimatedTax", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*6.25/100);
        model.addAttribute("totalPlusTax", (cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*newCartBasket.getEstimatedTax())/100+cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        model.addAttribute("cart", cartBasket);
        httpSession.setAttribute("cartCount",cartBasket.size());
        httpSession.setAttribute("totalPlusTax",(cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*newCartBasket.getEstimatedTax())/100+cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        httpSession.setAttribute("cart",cartBasket);
        httpSession.setAttribute("estimatedTax", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*6.25/100);
        log.warn(httpSession.getId());
        log.warn("addtocart call: " + cartBasket);
        return "redirect:/cart";
    }


    @GetMapping("/confirm_order")
    public String getCheckout(HttpSession httpSession, Model model, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, @ModelAttribute("product") Product product) {
        model.addAttribute("total", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        model.addAttribute("cartCount",cartBasket.size());
        model.addAttribute("cart", cartBasket);
        model.addAttribute("total", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        double totalPlusTax =Math.round((( cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*6.25)/100+cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum())*100)/100D;
        model.addAttribute("totalPlusTax", totalPlusTax);
        double estimatedTax = Math.round((cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum()*6.25/100)*100)/100D;
        model.addAttribute("estimatedTax", estimatedTax);
        cartBasket.forEach(s-> s.setUserSessionId(httpSession.getId()));
        httpSession.setAttribute("cart",cartBasket);
        System.out.println("----------in /checkout Cart Size"+cartBasket.size());
        System.out.println("-----------total count in /checkout" +cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        log.warn(httpSession.getId());
        log.warn("checkout call cartbasket: " + cartBasket);

        // cartBasket.setShippingAddress(cartBasket1.getShippingAddress());
        return "/confirm_order";

    }

    @GetMapping("/cart")
    public String getCart(HttpSession httpSession, Model model, @ModelAttribute("cart") ArrayList<CartBasket> cartBasket, @ModelAttribute("product") Product product){
        //Product product = new Product();
        model.addAttribute("cartCount",cartBasket.size());
        model.addAttribute("cart", cartBasket);
        System.out.println("----------in /cart Cart Size"+cartBasket.size());
        System.out.println("-----------in /cart Cart basket"+cartBasket);
        model.addAttribute("total", cartBasket.stream().mapToDouble(CartBasket::getProductPrice).sum());
        httpSession.setAttribute("cart",cartBasket);
        log.warn(httpSession.getId());
       // model.addAttribute("total", cartBasket.stream().mapToDouble(Product::getPrice).sum();
        //odel.addAttribute("cart",
        return "redirect:/home";
    }

}
