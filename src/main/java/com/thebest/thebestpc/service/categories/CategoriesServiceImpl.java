package com.thebest.thebestpc.service.categories;

import com.thebest.thebestpc.model.Categories;
import com.thebest.thebestpc.repository.CategoriesRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Override
    public Categories createNewCategory(Categories categories) {
        return null;
    }

    @Override
    public Categories findCategoryByName(String name) {
        return null;
    }

    @Override
    public Categories findCategoryById(Long id) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }

    @Override
    public Categories updateCategory(Categories categories) {
        return null;
    }

    @Override
    public List<Categories> findAllCategories() {
        return categoriesRepository.findAll();
    }
}
