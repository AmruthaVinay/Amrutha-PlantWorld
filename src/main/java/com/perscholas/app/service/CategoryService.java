package com.perscholas.app.service;

import com.perscholas.app.model.Category;
import com.perscholas.app.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
   private CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }


    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }


    public Category findById(Integer id) {
        Category category = categoryRepository.findById(id).get();
        return category;
    }

    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
