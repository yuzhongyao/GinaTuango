package com.example.ginatuango.services;

import com.example.ginatuango.data.entities.Category;
import com.example.ginatuango.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public void insertNewCategory(Category category){
        categoryRepository.save(category);
    }
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }


}
