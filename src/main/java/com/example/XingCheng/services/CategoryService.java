package com.example.XingCheng.services;

import com.example.XingCheng.data.entities.Address;
import com.example.XingCheng.data.entities.Category;
import com.example.XingCheng.repositories.AddressRepository;
import com.example.XingCheng.repositories.CategoryRepository;
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

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }
}
