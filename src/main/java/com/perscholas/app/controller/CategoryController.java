/* This class contains logic related to adding the category, deleting or updating category*/

package com.perscholas.app.controller;

import com.perscholas.app.model.Category;
import com.perscholas.app.model.Product;
import com.perscholas.app.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class CategoryController {
    CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/view_category")
    public String redirectToCategoryPage(Model model) throws Exception{
        model.addAttribute("category", categoryService.getAllCategory());
        return "redirect:/view_category";
    }

    @GetMapping("/view_category")
    public String viewCategoryPage(Model model) throws Exception{
        model.addAttribute("category", categoryService.getAllCategory());
        return "view_category";
    }

    @GetMapping("/admin/add_category")
    public String redirectToAddCategoryPage(@Valid Category category, BindingResult result, Model model) throws Exception {

        return "redirect:/add_category";
    }

    @GetMapping("/add_category")
    public String showAddCategoryPage(@Valid Category category, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            return "add_category";
        }

        return "add_category";
    }
    @PostMapping("/save_category")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception{
        log.warn("Category received in save category controller "+category);
        if (bindingResult.hasErrors()) {
            log.warn("has error" +category);
            return "redirect:/add_category";
        }
        category.setName(category.getName());
        category.setDescription(category.getDescription());
        categoryService.saveCategory(category);
        redirectAttributes.addFlashAttribute("message", "New Category Successfully Added/Updated");
        return "redirect:/view_category";
    }

    @GetMapping("/delete_category/{id}")
    public String deleteById(@PathVariable(name="id") Integer id, Model model) throws Exception{
        log.warn("Id in delete method"+id);
        categoryService.deleteCategoryById(id);
        return "redirect:/view_category";
    }


  @GetMapping("/update_category_form")
    public String requestEditOrUpdatePage(Model model, @RequestParam("category") int id) throws Exception {
        //log.warn("category received in update_category "+category.getId());
        //log.warn("category received in update_category "+category.getId());
        log.warn("In update category form");
        model.addAttribute("category",categoryService.findById(id));
       // redirectAttributes.getAttribute("category");
      //log.warn("In update category form"+redirectAttributes.getAttribute("category"));
        return "update_category";
    }


    @GetMapping("/update_category/{id}")
    public String requestUpdateById(@PathVariable int id,RedirectAttributes redirectAttributes) throws Exception {

        Category categoryFromDb = categoryService.findById(id);
        //model.addAttribute("category", categoryFromDb);
        redirectAttributes.addAttribute("category",categoryFromDb);
        log.warn("In update category/id");
        return "redirect:/update_category_form";
    }



}
