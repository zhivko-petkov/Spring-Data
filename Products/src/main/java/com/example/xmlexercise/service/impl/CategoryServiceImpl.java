package com.example.xmlexercise.service.impl;

import com.example.xmlexercise.model.dto.categoriesByProductsCount.CategoryRootInfoDto;
import com.example.xmlexercise.model.dto.seed.CategorySeedDto;
import com.example.xmlexercise.model.entity.Category;
import com.example.xmlexercise.repository.CategoryRepository;
import com.example.xmlexercise.service.CategoryService;
import com.example.xmlexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedCategories(List<CategorySeedDto> categories){
        if(categoryRepository.count() == 0){
            categories
                    .stream()
                    .filter(validationUtil::isValid)
                    .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                    .forEach(categoryRepository::save);
        }

    }

    @Override
    public long getEntityCount() {
        return categoryRepository.count();
    }

    @Override
    public Set<Category> getRandomCategories()
    {
        Set<Category> categories = new HashSet<>();
        long categoriesCount = categoryRepository.count();

        for (int i = 0; i < 2; i++) {
            long randomId = ThreadLocalRandom
                    .current().nextLong(1, categoriesCount);

            categories.add(categoryRepository
                    .findById(randomId)
                    .orElse(null));
        }

        return categories;
    }

    @Override
    public CategoryRootInfoDto getCategoryInfo() {
        CategoryRootInfoDto categoryRootInfoDto = new CategoryRootInfoDto();
        categoryRootInfoDto.setCategory(categoryRepository.getCategoriesInfo());

        return categoryRootInfoDto;

    }


}
