/* This class contains logic related to adding to viewing the product, adding new product, update product and viewing all different products*/

package com.perscholas.app.controller;

import com.perscholas.app.model.CartBasket;
import com.perscholas.app.model.Category;
import com.perscholas.app.model.Product;
import com.perscholas.app.model.UserRegistration;
import com.perscholas.app.repository.ProductRepository;
import com.perscholas.app.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
@Slf4j
@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/about_us")
    public String showAboutUsPage(Model model) {
        return "about_us";
    }
    @GetMapping("/tour")
    public String requestTourPage(Model model) {
        return "tour";
    }


    @GetMapping("/admin/view_product")
    public String redirectToViewProductPage(Model model) {
        //model.addAttribute("product", productService.findAll());
        return "redirect:/view_product";
    }

    @GetMapping("/view_product")
    public String requestViewProductPage(Model model) {
        model.addAttribute("product", productService.findAll());
        return "view_product";
    }

      @GetMapping("/admin/add_product")
    public String redirectToAddProductPage( @Valid Product product, BindingResult result, Model model) {

        return "redirect:/add_product";
    }
    @GetMapping("/add_product")
    public String showAddProductPage( @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_product";
        }

        return "add_product";
    }

    @PostMapping("/save_product")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        log.warn("product received in save product "+product);
        if (bindingResult.hasErrors()) {
            log.warn("has error" +product);
            return "redirect:/add_product";
        }
        else if(product.getDiscount()!=0 && product.getBeforeDiscountPrice()!=0){
            product.setPrice((product.getBeforeDiscountPrice())-(product.getBeforeDiscountPrice()*product.getDiscount()/100));
            log.warn("Discount" +product.getDiscount());
            log.warn("getBeforeDiscountPrice" +product.getBeforeDiscountPrice());
            log.warn("setPrice" +product.getPrice());
            productService.saveProduct(product);
        }
        else{
            product.setPrice(product.getPrice());
            productService.saveProduct(product);
        }

        redirectAttributes.addFlashAttribute("message", "New Product Succesfully Added");
       /* return "redirect:/add_product";*/
        return "redirect:/view_product";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteById(@PathVariable(name="id") Integer id,Model model) {
        log.warn("Id in delete method"+id);
        productService.deleteProductById(id);

        //  model.addAttribute("product",productFromDb );
        return "redirect:/view_product";
    }

    @GetMapping("/update_product")
    public String requestEditOrUpdatePage(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        log.warn("category received in update_category "+product.getId());
        return "update_product";
    }


    @GetMapping("/update_product/{id}")
    public String requestUpdateById(@Valid @ModelAttribute("product") Product product, BindingResult result,Model model,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            //log.error(bi);
            product.setId(product.getId());
            log.warn("in update_category/{id} ---------------has errors"+product.getId());
            //return "error";
        }
        Product productFromDb = productService.findById(product.getId());
        model.addAttribute("product", productFromDb);
        redirectAttributes.addAttribute("product", productService.findById(product.getId()));
        log.warn("in update_category/{id} ---------------");
        return "redirect:/update_product";
    }



}
