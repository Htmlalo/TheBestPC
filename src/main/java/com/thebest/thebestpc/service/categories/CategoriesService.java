package com.thebest.thebestpc.service.categories;

import com.thebest.thebestpc.model.Categories;

import java.util.List;

public interface CategoriesService {

    Categories createNewCategory(Categories categories);

    Categories findCategoryByName(String name);

    Categories findCategoryById(Long id);

    void deleteCategoryById(Long id);

    Categories updateCategory(Categories categories);

    List<Categories> findAllCategories();

}
